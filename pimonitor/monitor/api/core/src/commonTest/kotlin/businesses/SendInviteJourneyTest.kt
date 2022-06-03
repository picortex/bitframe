package businesses

import bitframe.core.signin.SignInParams
import expect.expect
import later.await
import pimonitor.client.MonitorApiTest
import pimonitor.client.runSequence
import pimonitor.core.businesses.params.CreateMonitoredBusinessParams
import pimonitor.core.businesses.params.InviteToShareReportsParams
import pimonitor.core.invites.Invite
import pimonitor.core.signup.params.SignUpBusinessParams
import kotlin.test.Test

class SendInviteJourneyTest {
    val api = MonitorApiTest()

    @Test
    fun should_send_invite_to_contact() = runSequence {
        step("If not registered, signup as business or individual") {
            val params = SignUpBusinessParams(
                businessName = "Invitor LLC",
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

//        step("Create a business to monitored") {
//            val params = CreateMonitoredBusinessParams(
//                businessName = "PiCortex LLC",
//                contactName = "Steven Sajja",
//                contactEmail = "ssajja@picortex.com"
//            )
//            val res = api.businesses.create(params).await()
//            expect(res.params.businessName).toBe("PiCortex LLC")
//        }

        step("Create a business to monitored") {
            val params = CreateMonitoredBusinessParams(
                businessName = "aSoft Ltd",
                contactName = "Anderson Lameck",
                contactEmail = "andylamax@programmer.net",
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

        step("Ensure invite is sent") {
            val res = api.businesses.all().await().first()
            expect(res.invites).toContainElements()
        }

        step("Load invite to see if it has the correct details") {
            val i = api.invites.load(invite?.uid ?: error("Invite was not created")).await()
            expect(i.invitorName).toBe("Invitor LLC")
        }
    }
}