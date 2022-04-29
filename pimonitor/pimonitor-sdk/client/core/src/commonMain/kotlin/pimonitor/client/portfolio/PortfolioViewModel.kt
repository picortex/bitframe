package pimonitor.client.portfolio

import bitframe.client.UIScopeConfig
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import later.await
import pimonitor.client.utils.live.update
import pimonitor.core.businesses.MonitoredBusinessBasicInfo
import pimonitor.core.portfolio.MonitoredBusinessPortfolio
import presenters.cases.MissionState
import presenters.cases.copy
import viewmodel.ViewModel
import pimonitor.client.portfolio.PortfolioIntent as Intent

internal class PortfolioViewModel(
    private val config: UIScopeConfig<PortfolioService>
) : ViewModel<Intent, MissionState<MonitoredBusinessBasicInfo, MonitoredBusinessPortfolio>>(
    initialState = MissionState.Loading(DEFAULT_LOADING_MESSAGE), config = config.viewModel
) {
    val service get() = config.service

    companion object {
        private const val DEFAULT_LOADING_MESSAGE = "Loading your portfolio data, please wait . . ."
    }

    override fun CoroutineScope.execute(i: Intent) = when (i) {
        is Intent.LoadPortfolioData -> loadPortfolio(i)
    }

    private fun CoroutineScope.loadPortfolio(i: Intent.LoadPortfolioData) = launch {
        val state = ui.value
        flow {
            emit(state.copy(message = DEFAULT_LOADING_MESSAGE))
            val portfolio = service.load().await()
            emit(state.copy(context = portfolio.business, data = portfolio))
        }.catch {
            emit(state.copy(it) {
                onRetry { post(i) }
            })
        }.collect {
            ui.update { it }
        }
    }
}