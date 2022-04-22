package pimonitor.client.business.operations

import bitframe.client.UIScopeConfig
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import later.await
import pimonitor.client.utils.live.update
import pimonitor.core.dashboards.OperationalDifferenceBoard
import pimonitor.core.invites.InfoResults
import presenters.cases.MissionState
import viewmodel.ViewModel
import pimonitor.client.business.operations.BusinessOperationsIntent as Intent

class BusinessOperationsViewModel(
    private val config: UIScopeConfig<BusinessOperationsService>
) : ViewModel<Intent, MissionState<InfoResults<OperationalDifferenceBoard>>>(DEFAULT_LOADING_STATE, config.viewModel) {
    companion object {
        val DEFAULT_LOADING_STATE = MissionState.Loading("Loading operational dashboard, please wait . . .")
    }

    private val service get() = config.service

    override fun CoroutineScope.execute(i: Intent): Any = when (i) {
        is Intent.LoadOperationalDashboard -> loadOperationalDashboard(i)
    }

    private fun CoroutineScope.loadOperationalDashboard(i: Intent.LoadOperationalDashboard) = launch {
        flow {
            emit(DEFAULT_LOADING_STATE)
            emit(MissionState.Success(service.dashboard(i.params).await()))
        }.catch {
            emit(MissionState.Failure(it) {
                onRetry { post(i) }
            })
        }.collect {
            ui.update { it }
        }
    }
}