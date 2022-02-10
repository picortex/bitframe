package pimonitor.portfolio

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import pimonitor.PiMonitorScopeConfig
import viewmodel.ViewModel
import pimonitor.portfolio.PortfolioIntent as Intent
import pimonitor.portfolio.PortfolioState as State

class PortfolioViewModel(
    private val config: PiMonitorScopeConfig
) : ViewModel<Intent, State>(State.Loading("Loading portfolio . . "), config) {
//    private val service get() = config.service.portfolio

    override fun CoroutineScope.execute(i: Intent) = when (i) {
        Intent.LoadPortfolio -> loadPortfolio()
    }

    private fun CoroutineScope.loadPortfolio() = launch {
        flow<State> {
            emit(State.Loading("Loading portfolio data, please wait . . ."))
//            emit(State.Portfolio(data = service.getPortfolioData().await()))
        }.catch {
            emit(State.Failure(it))
        }.collect {
            ui.value = it
        }
    }
}