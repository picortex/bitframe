package businesses

import bitframe.core.signin.SignInParams
import later.await
import pimonitor.client.businesses.BusinessesIntent
import pimonitor.client.businesses.BusinessesViewModel
import pimonitor.client.investments.fields.InvestmentFields
import pimonitor.client.runSequence
import pimonitor.core.businesses.params.CreateMonitoredBusinessParams
import pimonitor.core.signup.params.SignUpIndividualParams
import presenters.cases.Emphasis.Companion.Loading
import presenters.cases.Emphasis.Companion.Success
import presenters.cases.loading
import presenters.cases.success
import presenters.table.tabulateToConsole
import utils.PiMonitorTestScope
import utils.toContain
import viewmodel.expect
import kotlin.test.Test

class CaptureInvestmentJourneyTest {
    val scope = PiMonitorTestScope()
    val api = scope.api
    val vm = scope.businesses.viewModel as BusinessesViewModel

    @Test
    fun should_capture_investment_from_dialog() = runSequence {
        step("Sign Up as a Monitor") {
            val monitor = SignUpIndividualParams(
                name = "Jane $time Doe",
                email = "jane@doe$time.com",
                password = "jane"
            )
            api.signUp.signUp(monitor).await()
        }

        step("Sign in as the registered monitor") {
            val cred = SignInParams(
                identifier = "jane@doe$time.com",
                password = "jane"
            )
            api.signIn.signIn(cred).await()
        }

        step("Create a monitored business") {
            val params = CreateMonitoredBusinessParams(
                businessName = "PiCortex LLC",
                contactName = "Mohammed Majapa",
                contactEmail = "mmajapa@gmail$time.com",
                sendInvite = false
            )
            val state = vm.ui.value
            vm.expect(BusinessesIntent.SendCreateBusinessForm(params)).toContain(
                state.loading("Adding ${params.businessName}, please wait . . ."),
                state.success("${params.businessName} has successfully been added. Loading all your businesses, please wait . . ."),
            )
            vm.ui.value.table.tabulateToConsole()
            expect.expect(vm.ui.value.table.rows).toBeOfSize(1)
        }

        step("Should launch a capture investment dialog") {
            val business = api.businesses.all().await().first()
            vm.expect(BusinessesIntent.ShowCreateInvestmentForm(business, null))
            vm.ui.value.dialog?.asForm?.fields as InvestmentFields
        }
    }
}