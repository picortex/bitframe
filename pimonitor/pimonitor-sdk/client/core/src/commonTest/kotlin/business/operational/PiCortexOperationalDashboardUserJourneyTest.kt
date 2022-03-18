package business.operational

import bitframe.core.signin.SignInCredentials
import expect.expect
import later.await
import pimonitor.client.business.BusinessDetailsIntent
import pimonitor.client.business.financials.BusinessFinancialIntent
import pimonitor.client.business.operations.BusinessOperationsIntent
import pimonitor.client.runSequence
import pimonitor.core.businesses.params.CreateMonitoredBusinessParams
import pimonitor.core.businesses.params.CreateMonitoredBusinessResult
import pimonitor.core.businesses.params.InviteToShareReportsParams
import pimonitor.core.dashboards.OperationalDashboard
import pimonitor.core.invites.InfoResults
import pimonitor.core.invites.Invite
import pimonitor.core.picortex.AcceptPicortexInviteParams
import pimonitor.core.signup.params.IndividualSignUpParams
import presenters.feedbacks.Feedback
import presenters.state.State
import utils.PiMonitorTestScope
import utils.toContain
import viewmodel.expect
import kotlin.test.Test

class PiCortexOperationalDashboardUserJourneyTest {

    private val scope = PiMonitorTestScope()
    private val api get() = scope.api
    private val vm get() = scope.businessOperations.viewModel

    @Test
    fun should_load_picortex_dashboard_of_a_business_with_picortex_integration() = runSequence {
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
            val params = AcceptPicortexInviteParams(
                inviteId = i.uid,
                subdomain = "b2b",
                secret = "f225ela32hovtvo4s1bj466j1p"
            )
            api.invites.accept(params).await()
        }

        step("View PiCortex Operations Dashboard of ${result?.business?.name}") {
            vm.expect(BusinessOperationsIntent.LoadOperationalDashboard(invite!!.invitedBusinessId)).toContain(
                State.Loading("Loading operational dashboard, please wait . . ."),
            )
            val state = vm.ui.value as State.Content<InfoResults.Shared<OperationalDashboard>>
            expect(state.value.data).toBeNonNull()
        }
    }
}