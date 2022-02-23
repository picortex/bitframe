package pimonitor.client.portfolio

import bitframe.client.UIScopeConfig
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import later.await
import pimonitor.core.portfolio.PortfolioData
import presenters.feedbacks.Feedback
import viewmodel.ViewModel
import pimonitor.client.portfolio.PortfolioIntent as Intent
import pimonitor.client.portfolio.PortfolioState as State

class PortfolioViewModel(
    private val config: UIScopeConfig<PortfolioService>
) : ViewModel<Intent, State>(State(), config.viewModel) {
    val service get() = config.service
    override fun CoroutineScope.execute(i: Intent) = when (i) {
        Intent.LoadPortfolioData -> loadPortfolio()
    }

    private fun CoroutineScope.loadPortfolio() = launch {
        flow {
            emit(State(status = State.INITIAL_LOADING_STATUS))
            emit(State(status = Feedback.None, data = service.load().await()))
        }.catch {
            emit(State(status = Feedback.Failure(it)))
        }.collect {
            ui.value = it
        }
    }
}