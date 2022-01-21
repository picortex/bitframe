package integration.monitors

import bitframe.testing.annotations.Lifecycle
import bitframe.testing.annotations.TestInstance
import bitframe.testing.annotations.Testcontainers
import kotlinx.coroutines.delay
import kotlinx.coroutines.test.runTest
import later.await
import pimonitor.authentication.signup.SignUpParams
import pimonitor.authentication.signup.toCredentials
import pimonitor.evaluation.businesses.forms.CreateBusinessViewModel
import pimonitor.monitored.CreateMonitoredBusinessParams
import pimonitor.testing.PiMonitorIntegrationTest
import viewmodel.expect
import kotlin.test.Test
import pimonitor.evaluation.businesses.forms.CreateBusinessIntent as Intent
import pimonitor.evaluation.businesses.forms.CreateBusinessState as State

@Testcontainers
@TestInstance(Lifecycle.PER_CLASS)
class CreateBusinessViewModelTest : PiMonitorIntegrationTest() {
    val service get() = piMonitorService
    val vm by lazy { CreateBusinessViewModel(config) }

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