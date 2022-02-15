package pimonitor.client.portfolio

import bitframe.client.UIScopeConfig
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import pimonitor.client.PiMonitorApi
import viewmodel.ViewModel
import pimonitor.client.portfolio.PortfolioIntent as Intent
import pimonitor.client.portfolio.PortfolioState as State

class PortfolioViewModel(
    private val config: UIScopeConfig<PiMonitorApi>
) : ViewModel<Intent, State>(State.Loading("Loading portfolio . . "), config.viewModel) {
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