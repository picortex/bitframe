package businesses

import bitframe.core.signin.SignInCredentials
import datetime.SimpleDateTime
import expect.expect
import later.await
import pimonitor.client.PiMonitorApiTest
import pimonitor.client.runSequence
import pimonitor.core.business.investments.CaptureInvestmentsParams
import pimonitor.core.business.investments.InvestmentType
import pimonitor.core.businesses.MonitoredBusinessBasicInfo
import pimonitor.core.businesses.params.CreateMonitoredBusinessParams
import pimonitor.core.signup.params.BusinessSignUpParams
import kotlin.test.Test

class CaptureInvestmentJourneyTest {
    val api = PiMonitorApiTest()

    @Test
    fun should_capture_investments() = runSequence {
        step("If not registered, signup as business or individual") {
            val params = BusinessSignUpParams(
                businessName = "Invitor LLC",
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

        var business: MonitoredBusinessBasicInfo? = null
        step("Create a business to monitored") {
            val params = CreateMonitoredBusinessParams(
                businessName = "PiCortex LLC",
                contactName = "Steven Sajja",
                contactEmail = "ssajja@picortex.com"
            )
            business = api.businesses.create(params).await().business
            expect(business?.name).toBe("PiCortex LLC")
        }

        step("Capture Investment of the newly created business") {
            val params = CaptureInvestmentsParams(
                businessId = business!!.uid,
                name = "Asset Capital",
                type = InvestmentType.Loan.name,
                source = "aSoft Ltd",
                amount = 30_000.0,
                date = SimpleDateTime.now.timeStampInMillis,
                details = "Test details"
            )
            val investment = api.businessInvestments.capture(params).await()
            expect(investment.name).toBe("Asset Capital")
            expect(investment.amount).toBe(30_000.0)
        }

        step("Load to ensure that the captured investments") {
            val investments = api.businessInvestments.all(business!!.uid).await()
            expect(investments).toBeOfSize(1)

            val investment = investments.first()
            expect(investment.amount).toBe(30_000.0)
        }
    }
}