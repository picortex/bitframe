package pimonitor.client.business.operations

import bitframe.client.UIScopeConfig
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import later.await
import pimonitor.client.businesses.BusinessesService
import pimonitor.core.dashboards.OperationalDashboard
import pimonitor.core.invites.InfoResults
import presenters.cases.State
import viewmodel.ViewModel
import pimonitor.client.business.operations.BusinessOperationsIntent as Intent

class BusinessOperationsViewModel(
    private val config: UIScopeConfig<BusinessesService>
) : ViewModel<Intent, State<InfoResults<OperationalDashboard>>>(State.Loading(DEFAULT_LOADING_MESSAGE), config.viewModel) {
    companion object {
        const val DEFAULT_LOADING_MESSAGE = "Loading operational dashboard, please wait . . ."
    }

    private val service get() = config.service

    override fun CoroutineScope.execute(i: Intent): Any = when (i) {
        is Intent.LoadOperationalDashboard -> loadOperationalDashboard(i)
    }

    private fun CoroutineScope.loadOperationalDashboard(i: Intent.LoadOperationalDashboard) = launch {
        flow {
            emit(State.Loading(DEFAULT_LOADING_MESSAGE))
            emit(State.Content(service.operationalDashboard(i.params).await()))
        }.catch {
            emit(State.Failure(it) {
                onRetry { post(i) }
            })
        }.collect {
            ui.value = it
        }
    }
}