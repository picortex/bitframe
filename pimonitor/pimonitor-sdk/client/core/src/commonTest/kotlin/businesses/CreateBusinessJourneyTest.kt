package businesses

import bitframe.core.signin.SignInCredentials
import expect.expect
import later.await
import pimonitor.client.businesses.BusinessesDialogContent
import pimonitor.client.runSequence
import pimonitor.core.businesses.params.CreateMonitoredBusinessParams
import pimonitor.core.signup.params.IndividualSignUpParams
import presenters.feedbacks.Feedback
import utils.PiMonitorMockScope
import utils.toContain
import viewmodel.expect
import kotlin.test.Test
import pimonitor.client.businesses.BusinessesIntent as Intent
import pimonitor.client.businesses.BusinessesState as State

class CreateBusinessJourneyTest {

    val scope = PiMonitorMockScope()
    val api = scope.api
    val vm = scope.businesses.viewModel

    @Test
    fun should_create_a_business_without_sending_an_invite_easily() = runSequence {
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
    }

    @Test
    fun should_create_a_business_then_send_an_invite_afterwards() = runSequence {
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
                sendInvite = true
            )
            val state = State()
            vm.expect(Intent.SubmitCreateBusinessForm(params)).toContain(
                state.copy(status = Feedback.Loading("Adding ${params.businessName}, please wait . . .")),
                state.copy(status = Feedback.Success("${params.businessName} has successfully been added")),
            )
            expect(vm.ui.value.dialog).toBeNonNull()
            expect(vm.ui.value.dialog?.heading).toBe(BusinessesDialogContent.InviteToShareReports)
        }
    }
}