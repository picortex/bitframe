package businesses

import bitframe.core.signin.SignInCredentials
import expect.expect
import kotlinx.coroutines.test.runTest
import later.await
import pimonitor.client.PiMonitorApi
import pimonitor.client.PiMonitorApiTest
import pimonitor.core.businesses.params.CreateBusinessParams
import pimonitor.core.signup.params.BusinessSignUpParams
import kotlin.test.Test

class CreateBusinessFlowTest {
    val api: PiMonitorApi = PiMonitorApiTest()

    @Test
    fun should_successfully_create_a_business() = runTest {
        // STEP 1. If not registered, signup as business or individual
        val params1 = BusinessSignUpParams(
            businessName = "Business Tester One",
            individualName = "Business Owner One",
            individualEmail = "business@tester1.com",
            password = "business@tester1.com"
        )
        val res1 = api.signUp.signUp(params1).await()
        expect(res1.user.name).toBe("Business Owner One")

        // STEP 2. Sign in with your registered account
        val params2 = SignInCredentials(
            identifier = "business@tester1.com",
            password = "business@tester1.com"
        )
        val res2 = api.signIn.signIn(params2).await()
        expect(res2.user.name).toBe("Business Owner One")

        // STEP 3. Create a business
        val params3 = CreateBusinessParams(
            businessName = "Tested Business One Ltd",
            contactName = "Contact One",
            contactEmail = "contact1@contact1.com"
        )
        val res3 = api.businesses.create(params3).await()
        expect(res3.businessName).toBe("Tested Business One Ltd")

        // STEP 4. Ensure business is registered
        val res4 = api.businesses.all().await().firstOrNull()
        expect(res4?.name).toBe("Tested Business One Ltd")
    }
}