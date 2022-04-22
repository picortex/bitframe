package businesses

import bitframe.core.signin.SignInParams
import datetime.Date
import expect.expect
import kotlinx.datetime.DatePeriod
import later.await
import pimonitor.client.PiMonitorApiTest
import pimonitor.client.runSequence
import pimonitor.core.businesses.params.CreateMonitoredBusinessParams
import pimonitor.core.interventions.params.InterventionParams
import pimonitor.core.signup.params.SignUpBusinessParams
import pimonitor.core.utils.disbursables.disbursements.params.DisbursableDisbursementParams
import pimonitor.core.utils.disbursables.filters.DisbursableFilter
import kotlin.test.Test

class InterventionJourneyTest {
    val api = PiMonitorApiTest()

    @Test
    fun should_be_able_to_intervene_a_business() = runSequence {
        step("If not registered, signup as business or individual") {
            val params = SignUpBusinessParams(
                businessName = "Test Business $time",
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

        val business = step("Create a business") {
            val params = CreateMonitoredBusinessParams(
                businessName = "Test Monitored Business $time",
                contactName = "Test Contact $time",
                contactEmail = "contact@business$time.com"
            )
            val res = api.businesses.create(params).await()
            expect(res.params.businessName).toBe("Test Monitored Business $time")
            res.business
        }

        step("Intervene for the newly created business") {
            val today = Date.today()
            val deadline = today + DatePeriod(days = 3)
            val params = InterventionParams(
                businessId = business.uid,
                name = "Working Capital",
                date = today.toIsoFormat(),
                deadline = deadline.toIsoFormat(),
                amount = 30_000.0.toString(),
                recommendations = "Make sure the API is well tested"
            )
            val res = api.interventions.create(params).await()
            expect(res.name).toBe("Working Capital")
        }
    }

    @Test
    fun should_be_able_to_intervene_a_business_and_add_a_disbursement() = runSequence {
        step("If not registered, signup as business or individual") {
            val params = SignUpBusinessParams(
                businessName = "Test Business $time",
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

        val business = step("Create a business") {
            val params = CreateMonitoredBusinessParams(
                businessName = "Test Monitored Business $time",
                contactName = "Test Contact $time",
                contactEmail = "contact@business$time.com"
            )
            val res = api.businesses.create(params).await()
            expect(res.params.businessName).toBe("Test Monitored Business $time")
            res.business
        }

        val intervention = step("Intervene for the newly created business") {
            val today = Date.today()
            val deadline = today + DatePeriod(days = 3)
            val params = InterventionParams(
                businessId = business.uid,
                name = "Working Capital",
                date = today.toIsoFormat(),
                deadline = deadline.toIsoFormat(),
                amount = 100_000.0.toString(),
                recommendations = "Make sure the API is well tested"
            )
            api.interventions.create(params).await().also { expect(it.name).toBe("Working Capital") }
        }

        step("Add a disbursement to that newly created intervention") {
            val params = DisbursableDisbursementParams(
                disbursableId = intervention.uid,
                amount = 10_000.0.toString(),
                date = Date.today().toIsoFormat()
            )
            val disbursement = api.interventions.createDisbursement(params).await()
            expect(disbursement.amount.amount).toBe(1_000_000)
        }

        step("Ensure all things are disbursed correctly") {
            val i = api.interventions.all(DisbursableFilter(business.uid)).await().first()
            expect(i.totalDisbursed.amount).toBe(1_000_000)
            expect(i.disbursementProgressInPercentage.asInt).toBe(10)
        }
    }
}