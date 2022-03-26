@file:OptIn(ExperimentalTime::class)

package integrations.picortex

import bitframe.core.signin.SignInCredentials
import expect.expect
import later.await
import pimonitor.client.PiMonitorApiTest
import pimonitor.client.runSequence
import pimonitor.core.business.params.LoadReportParams
import pimonitor.core.businesses.DASHBOARD_OPERATIONAL
import pimonitor.core.businesses.params.CreateMonitoredBusinessParams
import pimonitor.core.businesses.params.InviteToShareReportsParams
import pimonitor.core.invites.InfoResults
import pimonitor.core.invites.Invite
import pimonitor.core.picortex.AcceptPicortexInviteParams
import pimonitor.core.signup.params.BusinessSignUpParams
import kotlin.test.Test
import kotlin.time.Duration.Companion.days
import kotlin.time.ExperimentalTime

class PiCortexDashboardIntegrationTest {
    val api = PiMonitorApiTest()

    @Test
    fun should_load_dashboard_after_credentials_have_been_captured() = runSequence {
        step("If not registered, signup as business or individual") {
            val params = BusinessSignUpParams(
                businessName = "PiCortex Int Ltd",
                individualName = "Business Owner $time",
                individualEmail = "business.owner@business$time.com",
                password = "business.owner@business$time.com",
            )
            val res = api.signUp.signUp(params).await()
            expect(res.user.name).toBe("Business Owner $time")
        }

        step("Sign in with your registered account") {
            val params = SignInCredentials(
                identifier = "business.owner@business$time.com",
                password = "business.owner@business$time.com",
            )
            val res = api.signIn.signIn(params).await()
            expect(res.user.name).toBe("Business Owner $time")
        }

        step("Create a business to monitored") {
            val params = CreateMonitoredBusinessParams(
                businessName = "aSoft Ltd",
                contactName = "Anderson Lameck",
                contactEmail = "andylamax@programmer.net"
            )
            val res = api.businesses.create(params).await()
            expect(res.params.businessName).toBe("aSoft Ltd")
        }

        var invite: Invite? = null
        step("Send an invite to the newly created business") {
            val business = api.businesses.all().await().first()
            val params = InviteToShareReportsParams(business)
            invite = api.invites.send(params).await()
            expect(invite?.invitedBusinessId).toBe(business.uid)
        }

        step("Accept invite by capturing picortex credentials") {
            val i = invite ?: error("Invite not is found")
            val params = AcceptPicortexInviteParams(
                inviteId = i.uid,
                subdomain = "b2b",
                secret = "f225ela32hovtvo4s1bj466j1p"
            )
            val res = api.invites.accept(params).await()
            expect(res.inviteId).toBe(i.uid)
        }

        step("Load businesses and make sure they come with picortex data") {
            val business = api.businesses.all().await().first()
            expect(business.operationalBoard).toBe(DASHBOARD_OPERATIONAL.PICORTEX)

            val end = time
            val start = time - 30.days
            val params = LoadReportParams(
                businessId = business.uid,
                start = start.toEpochMilliseconds().toDouble(),
                end = end.toEpochMilliseconds().toDouble()
            )
            val board = api.businesses.operationalDashboard(params).await() as InfoResults.Shared
            expect(board.data).toBeNonNull()
        }
    }
}