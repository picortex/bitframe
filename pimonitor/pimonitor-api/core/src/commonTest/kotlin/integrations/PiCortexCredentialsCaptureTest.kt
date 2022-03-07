package integrations

import bitframe.core.signin.SignInCredentials
import expect.expect
import later.await
import pimonitor.client.PiMonitorApiTest
import pimonitor.client.runSequence
import pimonitor.core.businesses.params.CreateMonitoredBusinessParams
import pimonitor.core.businesses.params.InviteToShareReportsParams
import pimonitor.core.invites.Invite
import pimonitor.core.picortex.AcceptPicortexInviteParams
import pimonitor.core.signup.params.BusinessSignUpParams
import kotlin.test.Test

class PiCortexCredentialsCaptureTest {
    val api = PiMonitorApiTest()

    @Test
    fun should_capture_picortex_credentials_after_invite_has_been_sent() = runSequence {
        step("If not registered, signup as business or individual") {
            val params = BusinessSignUpParams(
                businessName = "Test Business $time",
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
            invite = api.businesses.invite(params).await()
            expect(invite?.invitedBusinessId).toBe(business.uid)
        }

        step("Accept invite by capturing picortex credentials") {
            val i = invite ?: error("Invite is found")
            val params = AcceptPicortexInviteParams(
                inviteId = i.uid
            )
            val res = api.picortex.acceptInvite(params).await()
            expect(res.inviteId).toBe(i.uid)
        }
    }
}