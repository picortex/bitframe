package businesses

import bitframe.client.UIScopeConfig
import kotlinx.coroutines.delay
import kotlinx.coroutines.test.runTest
import later.await
import pimonitor.client.PiMonitorApiTest
import pimonitor.client.businesses.forms.CreateBusinessViewModel
import pimonitor.core.businesses.params.CreateBusinessParams
import pimonitor.core.signup.params.IndividualSignUpParams
import viewmodel.expect
import kotlin.test.Test
import pimonitor.client.businesses.forms.CreateBusinessIntent as Intent
import pimonitor.client.businesses.forms.CreateBusinessState as State

class CreateBusinessViewModelTest {
    val api = PiMonitorApiTest()
    val vm = CreateBusinessViewModel(UIScopeConfig(api, recoveryTime = 0L))

    @Test
    fun should_be_able_to_go_to_show_add_business_form_state() = runTest {
        vm.expect(Intent.ShowForm(null)).toBeIn<State.Form>()
    }

    @Test
    fun should_be_able_to_submit_add_business_form() = runTest {
        val monitor = IndividualSignUpParams(
            name = "Jane Doe",
            email = "jane@doe.com",
            password = "jane"
        )
        // signUp as a business
        api.signUp.signUp(monitor).await()
        api.signIn.signIn(monitor.toSignInCredentials()).await()
        delay(100)

        vm.expect(Intent.ShowForm(null)).toBeIn<State.Form>()
        val params = CreateBusinessParams(
            businessName = "PiCortex LLC",
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