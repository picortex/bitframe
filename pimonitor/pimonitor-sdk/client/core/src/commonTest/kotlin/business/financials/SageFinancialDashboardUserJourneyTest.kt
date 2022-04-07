package business.financials

import akkounts.reports.balancesheet.BalanceSheet
import akkounts.reports.incomestatement.IncomeStatement
import bitframe.core.signin.SignInParams
import expect.expect
import expect.toBe
import later.await
import pimonitor.client.business.financials.BusinessFinancialIntent
import pimonitor.client.runSequence
import pimonitor.core.businesses.params.CreateMonitoredBusinessParams
import pimonitor.core.businesses.params.InviteToShareReportsParams
import pimonitor.core.sage.AcceptSageOneInviteParams
import pimonitor.core.signup.params.SignUpIndividualParams
import utils.PiMonitorTestScope
import viewmodel.expect
import kotlin.test.Test

class SageFinancialDashboardUserJourneyTest {

    private val scope = PiMonitorTestScope()
    private val api get() = scope.api
    private val vm get() = scope.businessFinancials.viewModel

    @Test
    fun should_load_income_statement_of_a_business_with_sage_integration() = runSequence {
        step("Sign Up as a Monitor") {
            val monitor = SignUpIndividualParams(
                name = "Jane Doe",
                email = "jane@doe$time.com",
                password = "jane@doe$time.com"
            )
            api.signUp.signUp(monitor).await()
        }

        step("Sign in as the registered monitor") {
            val cred = SignInParams(
                identifier = "jane@doe$time.com",
                password = "jane@doe$time.com"
            )
            api.signIn.signIn(cred).await()
        }

        val result = step("Create a monitored business") {
            val params = CreateMonitoredBusinessParams(
                businessName = "aSoft Ltd",
                contactName = "Anderson Lameck",
                contactEmail = "andylamax@programmer.net",
                sendInvite = true
            )
            api.businesses.create(params).await()
        }

        val invite = step("Invite Business to share reports") {
            val params = InviteToShareReportsParams(result!!.summary)
            api.invites.send(params).await()
        }

        step("Accept invite to share sage reports params") {
            val params = AcceptSageOneInviteParams(
                inviteId = invite.uid,
                username = "mmajapa@gmail.com",
                password = "Rondebosch2016@",
                companyId = "468271",
            )
            api.invites.accept(params).await()
        }

        step("View Income Statement of the business under test") {
            val businessId = invite!!.invitedBusinessId
            vm.expect(BusinessFinancialIntent.LoadIncomeStatement(businessId))
            expect(vm.ui.value.report).toBe<IncomeStatement>()
        }
    }

    @Test
    fun should_load_balance_sheet_statement_of_a_business_with_sage_integration() = runSequence {
        step("Sign Up as a Monitor") {
            val monitor = SignUpIndividualParams(
                name = "Jane Doe",
                email = "jane@doe$time.com",
                password = "jane@doe$time.com"
            )
            api.signUp.signUp(monitor).await()
        }

        step("Sign in as the registered monitor") {
            val cred = SignInParams(
                identifier = "jane@doe$time.com",
                password = "jane@doe$time.com"
            )
            api.signIn.signIn(cred).await()
        }

        val result = step("Create a monitored business") {
            val params = CreateMonitoredBusinessParams(
                businessName = "aSoft Ltd",
                contactName = "Anderson Lameck",
                contactEmail = "andylamax@programmer.net",
                sendInvite = true
            )
            api.businesses.create(params).await()
        }

        val invite = step("Invite ${result.business.name} to share reports") {
            val params = InviteToShareReportsParams(result.summary)
            api.invites.send(params).await()
        }

        step("Accept invite to share sage reports params") {
            val params = AcceptSageOneInviteParams(
                inviteId = invite.uid,
                username = "mmajapa@gmail.com",
                password = "Rondebosch2016@",
                companyId = "468271",
            )
            api.invites.accept(params).await()
        }

        step("View Balance Sheet of the business under test") {
            val businessId = invite.invitedBusinessId
            vm.expect(BusinessFinancialIntent.LoadBalanceSheet(businessId))
            expect(vm.ui.value.report).toBe<BalanceSheet>()
        }
    }
}