package integrations.sage

import bitframe.core.signin.SignInParams
import expect.expect
import later.await
import pimonitor.client.PiMonitorApiTest
import pimonitor.client.runSequence
import pimonitor.core.businesses.DASHBOARD_FINANCIAL
import pimonitor.core.businesses.params.CreateMonitoredBusinessParams
import pimonitor.core.businesses.params.InviteToShareReportsParams
import pimonitor.core.invites.Invite
import pimonitor.core.sage.AcceptSageOneInviteParams
import pimonitor.core.signup.params.SignUpBusinessParams
import kotlin.test.Test

class SageDashboardIntegrationTest {
    val api = PiMonitorApiTest()

    @Test
    fun should_capture_sage_credentials_after_sent_invite_has_been_accepted() = runSequence {
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

        step("Accept invite by capturing sage credentials") {
            val i = invite ?: error("Invite not is found")
            val params = AcceptSageOneInviteParams(
                inviteId = i.uid,
                username = "mmajapa@gmail.com",
                password = "Rondebosch2016@",
                companyId = "468271",
            )
            val res = api.invites.accept(params).await()
            expect(res.inviteId).toBe(i.uid)
        }

        step("Load businesses and make sure they come with sage data") {
            val businesses = api.businesses.all().await()
            expect(businesses).toBeOfSize(1)
            val business = businesses.first()
            expect(business.financialBoard).toBe(DASHBOARD_FINANCIAL.SAGE_ONE)
            println(business.revenue)
            expect(business.revenue).toBeNonNull()
            expect(business.expenses).toBeNonNull()
            expect(business.grossProfit).toBeNonNull()
        }
    }
}