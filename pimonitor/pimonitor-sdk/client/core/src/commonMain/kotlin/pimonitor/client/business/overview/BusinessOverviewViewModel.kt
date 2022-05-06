package pimonitor.client.business.overview

import bitframe.client.UIScopeConfig
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import later.await
import pimonitor.client.PiMonitorApi
import pimonitor.client.utils.live.update
import pimonitor.core.business.overview.MonitoredBusinessOverview
import pimonitor.core.business.utils.info.toValidatedParams
import presenters.cases.MissionState
import presenters.cases.copy
import viewmodel.ViewModel

class BusinessOverviewViewModel(
    private val config: UIScopeConfig<PiMonitorApi>
) : ViewModel<BusinessOverviewIntent, MissionState<MonitoredBusinessOverview>>(
    MissionState.Loading(DEFAULT_LOADING_MESSAGE), config.viewModel
) {

    companion object {
        private val DEFAULT_LOADING_MESSAGE = "Loading overview for this business, please wait. . ."
    }

    private val api get() = config.service
    override fun CoroutineScope.execute(i: BusinessOverviewIntent): Any = when (i) {
        is BusinessOverviewIntent.LoadOverview -> loadOverview(i)
    }

    private fun CoroutineScope.loadOverview(i: BusinessOverviewIntent.LoadOverview) = launch {
        val state = ui.value
        flow {
            emit(state.copy(DEFAULT_LOADING_MESSAGE))
            val overview = api.businessOverview.load(i.params.toValidatedParams()).await()
            emit(state.copy(data = overview))
        }.catch {
            emit(state.copy(it) {
                onRetry { post(i) }
            })
        }.collect {
            ui.update { it }
        }
    }
}