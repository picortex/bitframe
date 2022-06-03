package businesses

import bitframe.core.signin.SignInParams
import expect.expect
import expect.toBe
import later.await
import pimonitor.client.MonitorApiTest
import pimonitor.client.runSequence
import pimonitor.core.business.utils.info.LoadInfoParams
import pimonitor.core.businesses.params.CreateMonitoredBusinessParams
import pimonitor.core.invites.InfoResults
import pimonitor.core.signup.params.SignUpBusinessParams
import kotlin.test.Test

class BusinessOperationsServiceTest {

    val api = MonitorApiTest()

    @Test
    fun should_return_a_not_shared_instance_of_a_business_with_not_shared_operational_info() = runSequence {
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

        val business = step("Create a business to monitored") {
            val params = CreateMonitoredBusinessParams(
                businessName = "PiCortex LLC",
                contactName = "Steven Sajja",
                contactEmail = "ssajja@picortex.com"
            )
            val res = api.businesses.create(params).await()
            expect(res.business.name).toBe("PiCortex LLC")
            res.business
        }

        step("Load Operation Info for the registered business") {
            val board = api.businessOperations.dashboard(LoadInfoParams(business.uid)).await()
            expect(board).toBe<InfoResults.NotShared>()
        }
    }
}