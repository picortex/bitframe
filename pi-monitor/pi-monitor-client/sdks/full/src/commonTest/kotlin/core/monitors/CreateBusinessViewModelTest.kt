package core.monitors

import kotlinx.coroutines.delay
import kotlinx.coroutines.runTest
import later.await
import pimonitor.PiMonitorService
import pimonitor.authentication.signup.SignUpParams
import pimonitor.authentication.signup.toCredentials
import pimonitor.evaluation.businesses.forms.CreateBusinessViewModel
import pimonitor.monitored.CreateMonitoredBusinessParams
import testing.IntegrationTest
import testing.annotations.Lifecycle
import testing.annotations.TestInstance
import testing.annotations.Testcontainers
import viewmodel.expect
import kotlin.test.Test
import pimonitor.evaluation.businesses.forms.CreateBusinessIntent as Intent
import pimonitor.evaluation.businesses.forms.CreateBusinessState as State

@Testcontainers
@TestInstance(Lifecycle.PER_CLASS)
abstract class CreateBusinessViewModelTest : IntegrationTest() {
    abstract val service: PiMonitorService

    val vm by lazy {
        CreateBusinessViewModel(service.monitors, service.businesses)
    }

    @Test
    fun should_be_able_to_go_to_show_add_business_form_state() = runTest {
        vm.expect(Intent.ShowForm(null)).toBeIn<State.Form>()
    }

    @Test
    fun should_be_able_to_submit_add_business_form() = runTest {
        val monitor = SignUpParams.Individual(
            name = "Jane Doe",
            email = "jane@doe.com",
            password = "jane"
        )
        // signUp as a business
        service.signUp.signUp(monitor).await()
        service.signIn.signIn(monitor.toCredentials())
        delay(100)
        vm.expect(Intent.ShowForm(null)).toBeIn<State.Form>()
        val params = CreateMonitoredBusinessParams(
            businessName = "PiCortext LLC",
            contactName = "Mohammed Majapa",
            contactEmail = "mmajapa@gmail.com",
            sendInvite = false
        )
        vm.expect(Intent.SubmitForm(params)).toGoThrough(
            State.Loading("Adding business, please wait . . ."),
            State.Success("Business Added")
        )
    }
}