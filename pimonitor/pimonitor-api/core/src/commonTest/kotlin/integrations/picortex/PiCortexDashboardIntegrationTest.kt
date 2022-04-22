@file:OptIn(ExperimentalTime::class)

package integrations.picortex

import bitframe.core.signin.SignInParams
import datetime.Date
import expect.expect
import kotlinx.datetime.DatePeriod
import later.await
import pimonitor.client.PiMonitorApiTest
import pimonitor.client.runSequence
import pimonitor.core.business.utils.info.LoadInfoParams
import pimonitor.core.businesses.DASHBOARD_OPERATIONAL
import pimonitor.core.businesses.params.CreateMonitoredBusinessParams
import pimonitor.core.businesses.params.InviteToShareReportsParams
import pimonitor.core.invites.InfoResults
import pimonitor.core.invites.Invite
import pimonitor.core.picortex.AcceptPicortexInviteParams
import pimonitor.core.signup.params.SignUpBusinessParams
import kotlin.test.Test
import kotlin.time.Duration.Companion.days
import kotlin.time.ExperimentalTime

class PiCortexDashboardIntegrationTest {
    val api = PiMonitorApiTest()

    @Test
    fun should_load_dashboard_after_credentials_have_been_captured() = runSequence {
        step("If not registered, signup as business or individual") {
            val params = SignUpBusinessParams(
                businessName = "PiCortex Int Ltd",
                individualName = "Business Owner $time",
                individualEmail = "business.owner@business$time.com",
                password = "business.owner@business$time.com",
            )
            val res = api.signUp.signUp(params).await()
            expect(res.user.name).toBe("Business Owner $time")
        }

        step("Sign in with your registered account") {
            val params = SignInParams(
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

            val end = Date.today()
            val start = end - DatePeriod(days = 30)
            val params = LoadInfoParams(business.uid, start, end)
            val board = api.businessOperations.dashboard(params).await() as InfoResults.Shared
            expect(board.data).toBeNonNull()
        }
    }
}