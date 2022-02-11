package unit

import kotlinx.coroutines.test.runTest
import pimonitor.PiMonitorScopeConfig
import pimonitor.api.PiMonitorServiceMock
import pimonitor.portfolio.PortfolioViewModel
import viewmodel.expect
import kotlin.test.Ignore
import kotlin.test.Test
import pimonitor.portfolio.PortfolioIntent as Intent
import pimonitor.portfolio.PortfolioState as State

class PortfolioViewModelTest {
    val service = PiMonitorServiceMock()
    val config = PiMonitorScopeConfig(service)

    val vm = PortfolioViewModel(config)

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