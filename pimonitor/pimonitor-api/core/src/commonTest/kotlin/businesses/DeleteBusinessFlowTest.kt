package businesses

import bitframe.core.signin.SignInCredentials
import expect.expect
import kotlinx.coroutines.test.runTest
import kotlinx.datetime.Clock
import later.await
import pimonitor.client.PiMonitorApi
import pimonitor.client.PiMonitorApiTest
import pimonitor.core.businesses.params.CreateMonitoredBusinessParams
import pimonitor.core.signup.params.BusinessSignUpParams
import kotlin.test.Test

class DeleteBusinessFlowTest {
    val api: PiMonitorApi = PiMonitorApiTest()

    @Test
    fun should_successfully_delete_a_business() = runTest {
        // STEP 1. If not registered, signup as business or individual
        val time = Clock.System.now()

        val params1 = BusinessSignUpParams(
            businessName = "Test Business $time",
            individualName = "Business Owner $time",
            individualEmail = "business.owner@business$time.com",
            password = "business.owner@business$time.com",
        )
        val res1 = api.signUp.signUp(params1).await()
        expect(res1.user.name).toBe("Business Owner $time")

        // STEP 2. Sign in with your registered account
        val params2 = SignInCredentials(
            identifier = "business.owner@business$time.com",
            password = "business.owner@business$time.com",
        )
        val res2 = api.signIn.signIn(params2).await()
        expect(res2.user.name).toBe("Business Owner $time")

        // STEP 3. Create a business
        val params3 = CreateMonitoredBusinessParams(
            businessName = "Test Monitored Business $time",
            contactName = "Test Contact $time",
            contactEmail = "contact@business$time.com"
        )
        val res3 = api.businesses.create(params3).await()
        expect(res3.businessName).toBe("Test Monitored Business $time")

        // STEP 4. Ensure business is registered
        val res4 = api.businesses.all().await().first()
        expect(res4.name).toBe("Test Monitored Business $time")

        // STEP 5. Delete business
        val res5 = api.businesses.delete(res4.uid).await().first()
        expect(res5.uid).toBe(res4.uid)

        // STEP 6: Ensure business is deleted
        val res6 = api.businesses.all().await()
        expect(res6).toBeEmpty()
    }
}