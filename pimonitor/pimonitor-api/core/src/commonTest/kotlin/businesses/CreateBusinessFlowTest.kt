package businesses

import bitframe.core.signin.SignInCredentials
import expect.expect
import later.await
import pimonitor.client.PiMonitorApi
import pimonitor.client.PiMonitorApiTest
import pimonitor.client.runSequence
import pimonitor.core.businesses.params.CreateMonitoredBusinessParams
import pimonitor.core.signup.params.BusinessSignUpParams
import kotlin.test.Test

class CreateBusinessFlowTest {
    val api: PiMonitorApi = PiMonitorApiTest()

    @Test
    fun should_successfully_create_a_business() = runSequence {
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

        step("Create a business") {
            val params = CreateMonitoredBusinessParams(
                businessName = "Test Monitored Business $time",
                contactName = "Test Contact $time",
                contactEmail = "contact@business$time.com"
            )
            val res = api.businesses.create(params).await()
            expect(res.params.businessName).toBe("Test Monitored Business $time")
        }

        step("Ensure business is registered") {
            val res = api.businesses.all().await().firstOrNull()
            expect(res?.name).toBe("Test Monitored Business $time")
        }
    }

    @Test
    fun should_create_a_business_even_if_the_contact_is_already_in_the_system() = runSequence {
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

        step("Create a business") {
            val params = CreateMonitoredBusinessParams(
                businessName = "Test 1 Monitored Business $time",
                contactName = "Test Contact $time",
                contactEmail = "contact@business$time.com"
            )
            val res = api.businesses.create(params).await()
            expect(res.params.businessName).toBe("Test 1 Monitored Business $time")
        }

        step("Create another business with same contact email") {
            val params = CreateMonitoredBusinessParams(
                businessName = "Test 2 Monitored Business $time",
                contactName = "Test Contact $time",
                contactEmail = "contact@business$time.com"
            )
            val res = api.businesses.create(params).await()
            expect(res.params.businessName).toBe("Test 2 Monitored Business $time")
        }

        step("Ensure business is registered") {
            val res = api.businesses.all().await().map { it.name }
            expect(res).toContain(
                "Test 1 Monitored Business $time",
                "Test 2 Monitored Business $time"
            )
        }

        step("Ensure user has now multiple spaces") {
            api.session.signOut()
            val res = api.signIn.signIn(SignInCredentials("contact@business$time.com", "contact@business$time.com")).await()
            expect(res.spaces.map { it.name }).toContain(
                "Test 1 Monitored Business $time",
                "Test 2 Monitored Business $time"
            )
        }
    }
}