package businesses

import bitframe.core.signin.SignInParams
import expect.expect
import later.await
import pimonitor.client.businesses.BusinessesViewModel
import pimonitor.client.invites.fields.InviteToShareFields
import pimonitor.client.runSequence
import pimonitor.core.businesses.params.CreateMonitoredBusinessParams
import pimonitor.core.signup.params.SignUpBusinessParams
import pimonitor.core.signup.params.SignUpIndividualParams
import presenters.cases.Emphasis.Companion.Loading
import presenters.cases.Emphasis.Companion.Success
import presenters.cases.loading
import presenters.cases.success
import utils.PiMonitorTestScope
import utils.toContain
import viewmodel.expect
import kotlin.test.Test
import pimonitor.client.businesses.BusinessesIntent as Intent

class CreateBusinessJourneyTest {

    private val scope = PiMonitorTestScope()
    private val api = scope.api
    private val vm = scope.businesses.viewModel as BusinessesViewModel

    @Test
    fun should_create_a_business_without_sending_an_invite_easily() = runSequence {
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
            vm.expect(Intent.SendCreateBusinessForm(params)).toContain(
                state.loading("Adding ${params.businessName}, please wait . . ."),
                state.success("${params.businessName} has successfully been added. Loading all your businesses, please wait . . ."),
            )
            expect(vm.ui.value.table.rows).toBeOfSize(1)
        }
    }

    @Test
    fun an_individual_monitor_should_create_a_business_then_send_an_invite_afterwards() = runSequence {
        step("Sign Up as a Monitor") {
            val monitor = SignUpIndividualParams(
                name = "Jane Doe",
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
            val state = vm.ui.value
            vm.expect(Intent.SendCreateBusinessForm(params)).toContain(
                state.loading("Adding ${params.businessName}, please wait . . ."),
                state.success("${params.businessName} has successfully been added. Preparing invite form, please wait . . ."),
            )
            val fields = vm.ui.value.dialog?.asForm?.fields as InviteToShareFields
            expect(fields.message.value).toBe(
                "Jane Doe would like to invite you to share your operational & financial reports with them through PiMonitor"
            )
        }
    }

    @Test
    fun a_corporate_monitor_should_create_a_business_then_send_an_invite_afterwards() = runSequence {
        step("Sign Up as a Monitor") {
            val monitor = SignUpBusinessParams(
                businessName = "Jane Doe Inc",
                individualName = "Jane Doe",
                individualEmail = "jane@doe$time.com",
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
            val state = vm.ui.value
            vm.expect(Intent.SendCreateBusinessForm(params)).toContain(
                state.loading("Adding ${params.businessName}, please wait . . ."),
                state.success("${params.businessName} has successfully been added. Preparing invite form, please wait . . ."),
            )
            val fields = vm.ui.value.dialog?.asForm?.fields as InviteToShareFields
            expect(fields.message.value).toBe(
                "Jane Doe Inc would like to invite you to share your operational & financial reports with them through PiMonitor"
            )
        }
    }
}