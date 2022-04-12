package investments

import bitframe.client.expect
import expect.expect
import expect.toBe
import kotlinx.coroutines.test.runTest
import pimonitor.client.investments.InvestmentIntent
import pimonitor.core.investments.InvestmentSummary
import presenters.table.Table
import utils.PiMonitorTestScope
import kotlin.test.Test

class InvestmentsScopeTest {
    private val appScope = PiMonitorTestScope()
    private val api = appScope.api
    private val scope = appScope.investments

    @Test
    fun should_load_all_investments() = runTest {
        val state = scope.expect(InvestmentIntent.LoadAllInvestments).value.last()
        expect(state.table).toBe<Table<InvestmentSummary>>()
        TODO()
    }
}