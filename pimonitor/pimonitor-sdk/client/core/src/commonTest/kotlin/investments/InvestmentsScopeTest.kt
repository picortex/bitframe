package investments

import bitframe.client.expect
import bitframe.core.signin.SignInParams
import expect.expect
import expect.toBe
import later.await
import pimonitor.client.investments.InvestmentsIntent
import pimonitor.client.runSequence
import pimonitor.client.utils.disbursables.DisbursablesIntent
import pimonitor.core.businesses.params.CreateMonitoredBusinessParams
import pimonitor.core.investments.InvestmentSummary
import pimonitor.core.investments.params.InvestmentParams
import pimonitor.core.signup.params.SignUpIndividualParams
import presenters.cases.Emphasis
import presenters.table.EmptyTable
import presenters.table.Table
import presenters.table.tabulateToConsole
import utils.PiMonitorTestScope
import kotlin.test.Test

class InvestmentsScopeTest {
    private val appScope = PiMonitorTestScope()
    private val api = appScope.api
    private val scope = appScope.investments

    @Test
    fun should_have_empty_investments_when_no_investments_are_made() = runSequence {
        step("Sign Up as a Monitor") {
            val monitor = SignUpIndividualParams(
                name = "Jane $time Doe",
                email = "jane@doe$time.com",
                password = "jane"
            )
            api.signUp.signUp(monitor).await()
        }

        step("Sign in as the registered monitor") {
            val cred = SignInParams(
                identifier = "jane@doe$time.com",
                password = "jane"
            )
            api.signIn.signIn(cred).await()
        }

        step("Ensure that the investments are loaded") {
            val state = scope.expect(DisbursablesIntent.LoadAllDisbursables(null)).value.last()
            expect(state.table).toBe<EmptyTable<InvestmentSummary>>()
        }
    }

    @Test
    fun should_load_investments_from_all_businesses() = runSequence {
        step("Sign Up as a Monitor") {
            val monitor = SignUpIndividualParams(
                name = "Jane $time Doe",
                email = "jane@doe$time.com",
                password = "jane"
            )
            api.signUp.signUp(monitor).await()
        }

        step("Sign in as the registered monitor") {
            val cred = SignInParams(
                identifier = "jane@doe$time.com",
                password = "jane"
            )
            api.signIn.signIn(cred).await()
        }

        val business = step("Create a monitored business") {
            val params = CreateMonitoredBusinessParams(
                businessName = "PiCortex LLC",
                contactName = "Mohammed Majapa",
                contactEmail = "mmajapa@gmail$time.com",
                sendInvite = false
            )
            api.businesses.create(params).await().business
        }

        val investment = step("Capture an investment") {
            val params = InvestmentParams(
                businessId = business.uid,
                name = "Test Investment",
                type = "Grant",
                source = "Anderson Lameck",
                amount = "30000",
                date = "2022-04-02",
                details = "Test details"
            )
            api.investments.create(params).await()
        }

        step("should be able to load all investments") {
            val state = scope.expect(DisbursablesIntent.LoadAllDisbursables(null)).value.last()
            state.table.tabulateToConsole()
            expect(state.emphasis).toBe(Emphasis.None)
            expect(state.table).toBe<Table<InvestmentSummary>>()
            val inv = state.table.rows.firstOrNull { it.data.uid == investment.uid }
            expect(inv).toBeNonNull()
        }
    }
}