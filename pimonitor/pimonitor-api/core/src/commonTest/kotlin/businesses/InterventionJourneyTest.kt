package businesses

import bitframe.core.signin.SignInParams
import datetime.SimpleDateTime
import expect.expect
import later.await
import pimonitor.client.PiMonitorApiTest
import pimonitor.client.runSequence
import pimonitor.core.business.interventions.params.CreateInterventionDisbursementParams
import pimonitor.core.business.interventions.params.CreateInterventionParams
import pimonitor.core.businesses.params.CreateMonitoredBusinessParams
import pimonitor.core.signup.params.SignUpBusinessParams
import kotlin.test.Test
import kotlin.time.Duration.Companion.days

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
            val params = CreateInterventionParams(
                businessId = business.uid,
                name = "Working Capital",
                date = SimpleDateTime.now.timeStampInMillis,
                deadline = (time + 3.days).toEpochMilliseconds().toDouble(),
                amount = 30_000.0,
                recommendations = "Make sure the API is well tested"
            )
            val res = api.businessInterventions.create(params).await()
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
            val params = CreateInterventionParams(
                businessId = business.uid,
                name = "Working Capital",
                date = SimpleDateTime.now.timeStampInMillis,
                deadline = (time + 3.days).toEpochMilliseconds().toDouble(),
                amount = 100_000.0,
                recommendations = "Make sure the API is well tested"
            )
            val res = api.businessInterventions.create(params).await()
            expect(res.name).toBe("Working Capital")
            res
        }

        step("Add a disbursement to that newly created intervention") {
            val params = CreateInterventionDisbursementParams(
                interventionId = intervention.uid,
                amount = 10_000.0,
                date = time.toEpochMilliseconds().toDouble()
            )
            val disbursement = api.businessInterventions.disburse(params).await()
            expect(disbursement.amount).toBe(10_000.0)
        }

        step("Ensure all things are disbursed correctly") {
            val i = api.businessInterventions.all(business.uid).await().first()
            expect(i.totalDisbursed).toBe(10_000.0)
            println(i)
            println(i.totalDisbursed)
            println(i.disbursementProgressInPercentage)
            expect(i.disbursementProgressInPercentage.asInt).toBe(10)
        }
    }
}