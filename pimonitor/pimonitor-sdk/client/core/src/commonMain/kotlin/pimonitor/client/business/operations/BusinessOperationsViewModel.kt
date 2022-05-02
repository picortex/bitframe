package pimonitor.client.business.operations

import bitframe.client.UIScopeConfig
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import later.await
import pimonitor.client.utils.live.update
import pimonitor.core.businesses.MonitoredBusinessBasicInfo
import pimonitor.core.dashboards.OperationalDifferenceBoard
import pimonitor.core.invites.InfoResults
import presenters.cases.MissionState
import presenters.cases.copy
import viewmodel.ViewModel
import pimonitor.client.business.operations.BusinessOperationsIntent as Intent

class BusinessOperationsViewModel(
    private val config: UIScopeConfig<BusinessOperationsService>
) : ViewModel<Intent, MissionState<InfoResults<OperationalDifferenceBoard>>>(
    MissionState.Loading(DEFAULT_LOADING_MESSAGE), config.viewModel
) {
    companion object {
        private val DEFAULT_LOADING_MESSAGE = "Loading operational dashboard, please wait . . ."
    }

    private val service get() = config.service

    override fun CoroutineScope.execute(i: Intent): Any = when (i) {
        is Intent.LoadOperationalDashboard -> loadOperationalDashboard(i)
    }

    private fun CoroutineScope.loadOperationalDashboard(i: Intent.LoadOperationalDashboard) = launch {
        val state = ui.value
        flow {
            emit(state.copy(DEFAULT_LOADING_MESSAGE))
            emit(state.copy(data = service.dashboard(i.params).await()))
        }.catch {
            emit(state.copy(it) {
                onRetry { post(i) }
            })
        }.collect {
            ui.update { it }
        }
    }
}