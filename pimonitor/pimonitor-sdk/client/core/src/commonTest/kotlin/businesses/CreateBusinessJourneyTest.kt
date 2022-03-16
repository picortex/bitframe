package businesses

import bitframe.core.signin.SignInCredentials
import expect.expect
import later.await
import pimonitor.client.businesses.dialogs.InviteToShareReportsDialog
import pimonitor.client.businesses.forms.InviteToShareFormFields
import pimonitor.client.runSequence
import pimonitor.core.businesses.params.CreateMonitoredBusinessParams
import pimonitor.core.businesses.params.InviteToShareReportsRawParams
import pimonitor.core.signup.params.BusinessSignUpParams
import pimonitor.core.signup.params.IndividualSignUpParams
import presenters.feedbacks.Feedback
import presenters.modal.Dialog
import utils.PiMonitorTestScope
import utils.toContain
import viewmodel.expect
import kotlin.test.Test
import pimonitor.client.businesses.BusinessesIntent as Intent
import pimonitor.client.businesses.BusinessesState as State

class CreateBusinessJourneyTest {

    private val scope = PiMonitorTestScope()
    private val api = scope.api
    private val vm = scope.businesses.viewModel

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
            vm.expect(Intent.SendCreateBusinessForm(params)).toContain(
                state.copy(status = Feedback.Loading("Adding ${params.businessName}, please wait . . .")),
                state.copy(status = Feedback.Success("${params.businessName} has successfully been added")),
            )
            expect(vm.ui.value.table.rows).toBeOfSize(1)
        }
    }

    @Test
    fun an_individual_monitor_should_create_a_business_then_send_an_invite_afterwards() = runSequence {
        step("Sign Up as a Monitor") {
            val monitor = IndividualSignUpParams(
                name = "Jane Doe",
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
            vm.expect(Intent.SendCreateBusinessForm(params)).toContain(
                state.copy(status = Feedback.Loading("Adding ${params.businessName}, please wait . . .")),
                state.copy(status = Feedback.Success("${params.businessName} has successfully been added. Preparing invite form, please wait . . .")),
            )
            val dialog = vm.ui.value.dialog as InviteToShareReportsDialog
            expect(dialog.fields.message.value).toBe(
                "Jane Doe would like to invite you to share your operational & financial reports with them through PiMonitor"
            )
            expect(vm.ui.value.focus).toBeNonNull()
            expect(vm.ui.value.focus?.name).toBe("PiCortex LLC")
        }
    }

    @Test
    fun a_corporate_monitor_should_create_a_business_then_send_an_invite_afterwards() = runSequence {
        step("Sign Up as a Monitor") {
            val monitor = BusinessSignUpParams(
                businessName = "Jane Doe Inc",
                individualName = "Jane Doe",
                individualEmail = "jane@doe$time.com",
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
            vm.expect(Intent.SendCreateBusinessForm(params)).toContain(
                state.copy(status = Feedback.Loading("Adding ${params.businessName}, please wait . . .")),
                state.copy(status = Feedback.Success("${params.businessName} has successfully been added. Preparing invite form, please wait . . .")),
            )
            val dialog = vm.ui.value.dialog as InviteToShareReportsDialog
            expect(dialog.fields.message.value).toBe(
                "Jane Doe Inc would like to invite you to share your operational & financial reports with them through PiMonitor"
            )
        }
    }
}