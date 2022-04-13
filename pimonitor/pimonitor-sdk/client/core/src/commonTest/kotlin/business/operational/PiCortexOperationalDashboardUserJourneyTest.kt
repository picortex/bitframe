package business.operational

import bitframe.core.signin.SignInParams
import datetime.Date
import expect.expect
import kotlinx.datetime.DatePeriod
import later.await
import pimonitor.client.business.operations.BusinessOperationsIntent
import pimonitor.client.runSequence
import pimonitor.core.business.utils.info.LoadInfoParams
import pimonitor.core.businesses.params.CreateMonitoredBusinessParams
import pimonitor.core.businesses.params.CreateMonitoredBusinessResult
import pimonitor.core.businesses.params.InviteToShareReportsParams
import pimonitor.core.dashboards.OperationalDashboard
import pimonitor.core.invites.InfoResults
import pimonitor.core.invites.Invite
import pimonitor.core.picortex.AcceptPicortexInviteParams
import pimonitor.core.signup.params.SignUpIndividualParams
import presenters.cases.State
import presenters.date.last
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
            val monitor = SignUpIndividualParams(
                name = "Jane Doe",
                email = "jane@doe$time.com",
                password = "jane@doe$time.com"
            )
            api.signUp.signUp(monitor).await()
        }

        step("Sign in as the registered monitor") {
            val cred = SignInParams(
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
            val end = Date.today()
            val start = end - DatePeriod(days = 30)
            val params = LoadInfoParams(
                businessId = invite!!.invitedBusinessId,
                start = start,
                end = end
            )
            vm.expect(BusinessOperationsIntent.LoadOperationalDashboard(params)).toContain(
                State.Loading("Loading operational dashboard, please wait . . ."),
            )
            val state = vm.ui.value as State.Content<InfoResults.Shared<OperationalDashboard>>
            expect(state.value.data).toBeNonNull()
        }
    }
}