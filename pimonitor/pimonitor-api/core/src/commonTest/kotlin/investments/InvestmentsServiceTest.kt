package investments

import bitframe.core.signin.SignInParams
import datetime.Date
import expect.expect
import later.await
import pimonitor.client.PiMonitorApiTest
import pimonitor.client.runSequence
import pimonitor.core.businesses.params.CreateMonitoredBusinessParams
import pimonitor.core.investments.InvestmentType
import pimonitor.core.investments.params.InvestmentParams
import pimonitor.core.signup.params.SignUpBusinessParams
import pimonitor.core.utils.disbursables.disbursements.params.DisbursableDisbursementParams
import kotlin.test.Test

class InvestmentsServiceTest {
    val api = PiMonitorApiTest()

    @Test
    fun should_be_able_to_load_all_investment() = runSequence {
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
            api.businesses.create(params).await().business.also { expect(it.name).toBe("PiCortex LLC") }
        }

        step("Capture Investment of the newly created business") {
            val params = InvestmentParams(
                businessId = business.uid,
                name = "Asset Capital",
                type = InvestmentType.Loan.name,
                source = "aSoft Ltd",
                amount = 30_000.0.toString(),
                date = Date.today().toIsoFormat(),
                details = "Test details"
            )
            api.investments.create(params).await().also { expect(it.name).toBe("Asset Capital") }
        }

        step("Load all investments") {
            val investments = api.investments.all(null).await()
            expect(investments).toBeOfSize(1)
            investments.first().also { expect(it.amount.amount).toBe(3_000_000) }
        }
    }

    @Test
    fun should_be_able_to_load_a_single_investment() = runSequence {
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
            api.businesses.create(params).await().business.also { expect(it.name).toBe("PiCortex LLC") }
        }

        val investment = step("Capture Investment of the newly created business") {
            val params = InvestmentParams(
                businessId = business.uid,
                name = "Asset Capital",
                type = InvestmentType.Loan.name,
                source = "aSoft Ltd",
                amount = 30_000.0.toString(),
                date = Date.today().toIsoFormat(),
                details = "Test details"
            )
            api.investments.create(params).await().also { expect(it.name).toBe("Asset Capital") }
        }

        step("Load a single") {
            val inv = api.investments.load(investment.uid).await()
            expect(inv.name).toBe("Asset Capital")
        }
    }

    @Test
    fun should_be_able_to_respond_after_loading_a_single_investment() = runSequence {
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
            api.businesses.create(params).await().business.also { expect(it.name).toBe("PiCortex LLC") }
        }

        val investment = step("Capture Investment of the newly created business") {
            val params = InvestmentParams(
                businessId = business.uid,
                name = "Asset Capital",
                type = InvestmentType.Loan.name,
                source = "aSoft Ltd",
                amount = 30_000.0.toString(),
                date = Date.today().toIsoFormat(),
                details = "Test details"
            )
            api.investments.create(params).await().also { expect(it.name).toBe("Asset Capital") }
        }

        step("Load a single") {
            val inv = api.investments.load(investment.uid).await()
            expect(inv.name).toBe("Asset Capital")
        }

        step("Load all investments") {
            expect(api.investments.all().await()).toBeNonNull()
        }
    }

    @Test
    fun should_be_able_to_load_an_investment_with_a_disbursement() = runSequence {
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
            api.businesses.create(params).await().business.also { expect(it.name).toBe("PiCortex LLC") }
        }

        val investment = step("Capture Investment of the newly created business") {
            val params = InvestmentParams(
                businessId = business.uid,
                name = "Asset Capital",
                type = InvestmentType.Loan.name,
                source = "aSoft Ltd",
                amount = 30_000.0.toString(),
                date = Date.today().toIsoFormat(),
                details = "Test details"
            )
            api.investments.create(params).await().also { expect(it.name).toBe("Asset Capital") }
        }

        step("Create a disbursement") {
            api.investments.createDisbursement(
                DisbursableDisbursementParams(
                    disbursableId = investment.uid,
                    amount = "10000",
                    date = Date.today().toIsoFormat()
                )
            ).await()
        }

        step("Load a single investment") {
            val inv = api.investments.load(investment.uid).await()
            expect(inv.name).toBe("Asset Capital")
        }

        step("Load all investments") {
            expect(api.investments.all().await()).toBeNonNull()
        }
    }
}