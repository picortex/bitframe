package business

import bitframe.core.signin.SignInCredentials
import expect.expect
import later.await
import pimonitor.client.business.Intent
import pimonitor.client.business.State
import pimonitor.client.runSequence
import pimonitor.core.businesses.params.CreateMonitoredBusinessParams
import pimonitor.core.businesses.params.CreateMonitoredBusinessResult
import pimonitor.core.businesses.params.InviteToShareReportsParams
import pimonitor.core.invites.Invite
import pimonitor.core.picortex.AcceptPicortexInviteParams
import pimonitor.core.sage.AcceptSageOneInviteParams
import pimonitor.core.signup.params.IndividualSignUpParams
import presenters.feedbacks.Feedback
import utils.PiMonitorTestScope
import utils.toContain
import viewmodel.expect
import kotlin.test.Test

class SageFinancialDashboardUserJourneyTest {

    private val scope = PiMonitorTestScope()
    private val api get() = scope.api
    private val vm get() = scope.business.viewModel

    @Test
    fun should_load_financial_dashboard_of_a_business_with_sage_integration() = runSequence {
        step("Sign Up as a Monitor") {
            val monitor = IndividualSignUpParams(
                name = "Jane Doe",
                email = "jane@doe$time.com",
                password = "jane@doe$time.com"
            )
            api.signUp.signUp(monitor).await()
        }

        step("Sign in as the registered monitor") {
            val cred = SignInCredentials(
                identifier = "jane@doe$time.com",
                password = "jane@doe$time.com"
            )
            api.signIn.signIn(cred).await()
        }

        var result: CreateMonitoredBusinessResult? = null
        step("Create a monitored business") {
            val params = CreateMonitoredBusinessParams(
                businessName = "aSoft Ltd",
                contactName = "Anderson Lameck",
                contactEmail = "andylamax@programmer.net",
                sendInvite = true
            )
            result = api.businesses.create(params).await()
        }

        var invite: Invite? = null
        step("Invite ${result?.business?.name} to share reports") {
            val params = InviteToShareReportsParams(result!!.summary)
            invite = api.invites.send(params).await()
        }

        step("Accept invite to share picortex reports params") {
            val i = invite ?: error("Invite not is found")
            val params = AcceptSageOneInviteParams(
                inviteId = i.uid,
                username = "mmajapa@gmail.com",
                password = "Rondebosch2016@",
                companyId = "468271",
            )
            api.invites.accept(params).await()
        }

        step("View PiCortex Operations Dashboard of ${result?.business?.name}") {
            val state = State()
            vm.expect(Intent.LoadOperationDashboard(invite!!.invitedBusinessId)).toContain(
                state.copy(status = Feedback.Loading("Loading operational dashboard, please wait . . .")),
            )
            expect(vm.ui.value.operationDashboard).toBeNonNull()
        }
    }
}