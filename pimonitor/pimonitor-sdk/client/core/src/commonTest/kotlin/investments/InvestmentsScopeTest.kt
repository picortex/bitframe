package investments

import bitframe.client.expect
import bitframe.core.signin.SignInParams
import expect.expect
import expect.toBe
import kotlinx.coroutines.test.runTest
import later.await
import pimonitor.client.investments.InvestmentIntent
import pimonitor.client.runSequence
import pimonitor.core.investments.InvestmentSummary
import pimonitor.core.signup.params.SignUpIndividualParams
import presenters.table.EmptyTable
import presenters.table.Table
import utils.PiMonitorTestScope
import kotlin.test.Test

class InvestmentsScopeTest {
    private val appScope = PiMonitorTestScope()
    private val api = appScope.api
    private val scope = appScope.investments
    
    @Test
    fun should_load_all_investments() = runSequence {
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
            val state = scope.expect(InvestmentIntent.LoadAllInvestments).value.last()
            expect(state.table).toBe<EmptyTable<InvestmentSummary>>()
        }
    }
}