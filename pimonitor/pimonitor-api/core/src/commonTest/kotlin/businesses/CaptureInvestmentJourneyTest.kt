package businesses

import bitframe.core.signin.SignInParams
import datetime.Date
import expect.expect
import later.await
import pimonitor.client.PiMonitorApiTest
import pimonitor.client.runSequence
import pimonitor.core.investments.InvestmentType
import pimonitor.core.investments.params.InvestmentParams
import pimonitor.core.businesses.MonitoredBusinessBasicInfo
import pimonitor.core.businesses.params.CreateMonitoredBusinessParams
import pimonitor.core.signup.params.SignUpBusinessParams
import kotlin.test.Test

class CaptureInvestmentJourneyTest {
    val api = PiMonitorApiTest()

    @Test
    fun should_capture_investments() = runSequence {
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
            val params = InvestmentParams(
                businessId = business!!.uid,
                name = "Asset Capital",
                type = InvestmentType.Loan.name,
                source = "aSoft Ltd",
                amount = 30_000.0.toString(),
                date = Date.today().toIsoFormat(),
                details = "Test details"
            )
            val investment = api.businessInvestments.capture(params).await()
            expect(investment.name).toBe("Asset Capital")
            expect(investment.amount.amount).toBe(3_000_000)
        }

        step("Load to ensure that the captured investments") {
            val investments = api.businessInvestments.all(business!!.uid).await()
            expect(investments).toBeOfSize(1)

            val investment = investments.first()
            expect(investment.amount.amount).toBe(3_000_000)
        }
    }
}