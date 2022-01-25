package pimonitor.portfolio

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import pimonitor.PiMonitorViewModelConfig
import viewmodel.ViewModel
import pimonitor.portfolio.PortfolioIntent as Intent
import pimonitor.portfolio.PortfolioState as State

class PortfolioViewModel(config: PiMonitorViewModelConfig) : ViewModel<Intent, State>(State.Loading("Loading portfolio . . "), config) {
    override fun CoroutineScope.execute(i: Intent) = when (i) {
        Intent.LoadPortfolio -> loadPortfolio()
    }

    private fun CoroutineScope.loadPortfolio() = launch {
        flow<State> {
            emit(State.Loading("Loading portfolio data, please wait . . ."))

        }.catch {
            emit(State.Failure(it))
        }
    }
}