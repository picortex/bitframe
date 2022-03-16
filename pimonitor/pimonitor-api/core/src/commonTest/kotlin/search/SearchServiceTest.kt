package search

import bitframe.core.signin.SignInCredentials
import expect.expect
import kotlinx.coroutines.test.runTest
import kotlinx.datetime.Clock
import later.await
import pimonitor.client.PiMonitorApiTest
import pimonitor.core.businesses.params.CreateMonitoredBusinessParams
import pimonitor.core.search.SearchParams
import pimonitor.core.search.SearchResult
import pimonitor.core.signup.params.BusinessSignUpParams
import kotlin.test.Test

class SearchServiceTest {
    val api = PiMonitorApiTest()

    @Test
    fun can_search_businesses_easily() = runTest {
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
            businessName = "Test Search Monitored Business $time",
            contactName = "Test Search Contact $time",
            contactEmail = "contact@business$time.com"
        )
        val res3 = api.businesses.create(params3).await()
        expect(res3.params.businessName).toBe("Test Search Monitored Business $time")

        //STEP 4: Search with search key "test" and see if a business with that key can be found
        val res4 = api.search.search(SearchParams("test")).await().filterIsInstance<SearchResult.MonitoredBusiness>()
        expect(res4).toContainElements()
        expect(res4.first().name).toBe("Test Search Monitored Business $time")
    }

    @Test
    fun can_search_contacts_easily() = runTest {
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
            businessName = "Test Search Monitored Business $time",
            contactName = "Test Search Contact $time",
            contactEmail = "contact@business$time.com"
        )
        val res3 = api.businesses.create(params3).await()
        expect(res3.params.businessName).toBe("Test Search Monitored Business $time")

        //STEP 4: Search with search key "test" and see if a contact with that key can be found
        val res4 = api.search.search(SearchParams("test")).await().filterIsInstance<SearchResult.ContactPersonSummary>()
        expect(res4).toContainElements()
        expect(res4.first().name).toBe("Test Search Contact $time")
    }

    @Test
    fun should_return_an_empty_list_if_there_is_no_math() = runTest {
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

        // STEP 3: Search for a non existent entity
        val res3 = api.search.search(SearchParams("test-test-test-test")).await().filterIsInstance<SearchResult.ContactPersonSummary>()
        expect(res3).toBeEmpty()
    }
}