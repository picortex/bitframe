package portfolio

import kotlinx.coroutines.test.runTest
import pimonitor.client.portfolio.PortfolioViewModel
import utils.PiMonitorMockScope
import viewmodel.expect
import kotlin.test.Ignore
import kotlin.test.Test
import pimonitor.client.portfolio.PortfolioIntent as Intent
import pimonitor.client.portfolio.PortfolioState as State

class PortfolioViewModelTest {

    val scope = PiMonitorMockScope()

    val vm = scope.portfolio.viewModel as PortfolioViewModel

    @Test
    fun should_start_in_a_loading_tate() = runTest {
        expect(vm).toBeIn<State.Loading>()
    }

    @Ignore // cover this quickly mate
    @Test
    fun should_load_portfolio_data() = runTest {
        TODO()
        vm.expect(Intent.LoadPortfolio).toBeIn<State.Portfolio>()
    }
}