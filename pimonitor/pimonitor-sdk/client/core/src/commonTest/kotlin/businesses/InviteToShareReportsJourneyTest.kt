package businesses

import bitframe.core.signin.SignInCredentials
import expect.expect
import later.await
import pimonitor.client.businesses.BusinessesDialogContent
import pimonitor.client.runSequence
import pimonitor.core.businesses.params.CreateMonitoredBusinessParams
import pimonitor.core.businesses.params.InviteToShareReportsParams
import pimonitor.core.signup.params.IndividualSignUpParams
import presenters.feedbacks.Feedback
import utils.PiMonitorMockScope
import utils.toContain
import viewmodel.expect
import kotlin.test.Ignore
import kotlin.test.Test
import pimonitor.client.businesses.BusinessesIntent as Intent
import pimonitor.client.businesses.BusinessesState as State

class InviteToShareReportsJourneyTest {

    val scope = PiMonitorMockScope()
    val api = scope.api
    val vm = scope.businesses.viewModel

    @Test
    @Ignore
    fun should_invite_to_share_reports_for_a_loaded_business() = runSequence {
        step("Sign Up as a Monitor") {
            val monitor = IndividualSignUpParams(
                name = "Jane $time Doe",
                email = "jane@doe$time.com",
                password = "jane"
            )
            api.signUp.signUp(monitor).await()
        }

        step("Sign in as the registered monitor") {
            val cred = SignInCredentials(
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
            vm.expect(Intent.SubmitCreateBusinessForm(params)).toContain(
                state.copy(status = Feedback.Loading("Adding ${params.businessName}, please wait . . .")),
                state.copy(status = Feedback.Success("${params.businessName} has successfully been added")),
            )
            expect(vm.ui.value.table.rows).toBeOfSize(1)
        }

        step("Should launch an invite to share reports dialog") {
            val business = api.businesses.all().await().first()
            vm.expect(Intent.ShowInviteToShareReportsForm(business)).toGoThrough()
        }
    }
}