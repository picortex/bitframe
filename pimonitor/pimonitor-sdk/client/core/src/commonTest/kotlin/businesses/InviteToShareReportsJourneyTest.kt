package businesses

import bitframe.core.UserEmail
import bitframe.core.signin.SignInParams
import expect.expect
import later.await
import pimonitor.client.businesses.dialogs.InviteToShareReportsForm
import pimonitor.client.runSequence
import pimonitor.core.businesses.params.CreateMonitoredBusinessParams
import pimonitor.core.signup.params.SignUpIndividualParams
import presenters.cases.Feedback
import presenters.table.tabulateToConsole
import utils.PiMonitorTestScope
import utils.toContain
import viewmodel.expect
import kotlin.test.Test
import pimonitor.client.businesses.BusinessesIntent as Intent
import pimonitor.client.businesses.BusinessesState as State

class InviteToShareReportsJourneyTest {

    val scope = PiMonitorTestScope()
    val api = scope.api
    val vm = scope.businesses.viewModel

    @Test
    fun should_invite_to_share_reports_for_a_loaded_business() = runSequence {
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
            val state = State()
            vm.expect(Intent.SendCreateBusinessForm(params)).toContain(
                state.copy(status = Feedback.Loading("Adding ${params.businessName}, please wait . . .")),
                state.copy(status = Feedback.Success("${params.businessName} has successfully been added. Loading all your businesses, please wait . . .")),
            )
            vm.ui.value.table.tabulateToConsole()
            expect(vm.ui.value.table.rows).toBeOfSize(1)
        }

        step("Should launch an invite to share reports dialog") {
            val business = api.businesses.all().await().first()
            vm.expect(Intent.ShowInviteToShareReportsForm(business))
            val dialog = vm.ui.value.dialog as InviteToShareReportsForm
            expect(dialog.fields.to.value).toBe(business.contacts.first { it is UserEmail }.value)
        }
    }

    @Test
    fun should_load_businesses_even_if_invite_to_share_reports_is_cancelled() = runSequence {
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
                sendInvite = true
            )
            val state = State()
            vm.expect(Intent.SendCreateBusinessForm(params)).toContain(
                state.copy(status = Feedback.Loading("Adding ${params.businessName}, please wait . . .")),
                state.copy(status = Feedback.Success("${params.businessName} has successfully been added. Preparing invite form, please wait . . .")),
            )
            vm.ui.value.table.tabulateToConsole()
            expect(vm.ui.value.table.rows).toBeOfSize(1)
        }

        step("Should launch an invite to share reports dialog") {
            val business = api.businesses.all().await().first()
            vm.expect(Intent.ShowInviteToShareReportsForm(business))
            val dialog = vm.ui.value.dialog as InviteToShareReportsForm
            expect(dialog.fields.to.value).toBe(business.contacts.first { it is UserEmail }.value)
        }

        step("Cancel sending invite should return to table with businesses") {
            val dialog = vm.ui.value.dialog as InviteToShareReportsForm
            dialog.cancel()
            expect(vm.ui.value.table.rows).toBeOfSize(1)
        }
    }
}