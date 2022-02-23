package pimonitor.client.portfolio

import bitframe.client.UIScopeConfig
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import pimonitor.core.portfolio.PortfolioService
import presenters.feedbacks.Feedback
import viewmodel.ViewModel
import pimonitor.client.portfolio.PortfolioIntent as Intent
import pimonitor.client.portfolio.PortfolioState as State

class PortfolioViewModel(
    private val config: UIScopeConfig<PortfolioService>
) : ViewModel<Intent, State>(State(), config.viewModel) {
    override fun CoroutineScope.execute(i: Intent) = when (i) {
        Intent.LoadPortfolioData -> loadPortfolio()
    }

    private fun CoroutineScope.loadPortfolio() = launch {
        val state = ui.value
        flow {
            emit(state.copy(status = State.INITIAL_LOADING_STATUS))
//            emit(State.Portfolio(data = service.getPortfolioData().await()))
        }.catch {
            emit(state.copy(status = Feedback.Failure(it)))
            delay(config.viewModel.recoveryTime)
            emit(state.copy(status = Feedback.None))
        }.collect {
            ui.value = it
        }
    }
}