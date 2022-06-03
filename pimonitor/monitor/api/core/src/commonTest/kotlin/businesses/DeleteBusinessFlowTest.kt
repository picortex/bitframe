package businesses

import bitframe.core.signin.SignInParams
import expect.expect
import later.await
import pimonitor.client.MonitorApi
import pimonitor.client.MonitorApiTest
import pimonitor.client.runSequence
import pimonitor.core.businesses.params.CreateMonitoredBusinessParams
import pimonitor.core.signup.params.SignUpBusinessParams
import kotlin.test.Test

class DeleteBusinessFlowTest {
    val api: MonitorApi = MonitorApiTest()

    @Test
    fun should_successfully_delete_a_business() = runSequence {
        step("If not registered, signup as business or individual") {
            val params1 = SignUpBusinessParams(
                businessName = "Test Business $time",
                individualName = "Business Owner $time",
                individualEmail = "business.owner@business$time.com",
                password = "business.owner@business$time.com",
            )
            val res1 = api.signUp.signUp(params1).await()
            expect(res1.user.name).toBe("Business Owner $time")
        }

        step("Sign in with your registered account") {
            val params2 = SignInParams(
                identifier = "business.owner@business$time.com",
                password = "business.owner@business$time.com",
            )
            val res2 = api.signIn.signIn(params2).await()
            expect(res2.user.name).toBe("Business Owner $time")
        }

        step("Create a business") {
            val params3 = CreateMonitoredBusinessParams(
                businessName = "Test Monitored Business $time",
                contactName = "Test Contact $time",
                contactEmail = "contact@business$time.com"
            )
            val res3 = api.businesses.create(params3).await()
            expect(res3.params.businessName).toBe("Test Monitored Business $time")
        }

        val business = step("Ensure business is registered") {
            val res4 = api.businesses.all().await().first()
            expect(res4.name).toBe("Test Monitored Business $time")
            res4
        }

        step("Delete business") {
            val res5 = api.businesses.delete(business.uid).await().first()
            expect(res5.uid).toBe(business.uid)
        }

        step("Ensure business is deleted") {
            val res6 = api.businesses.all().await()
            expect(res6).toBeEmpty()
        }
    }
}