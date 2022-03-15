package pimonitor.client.business

import bitframe.client.UIScopeConfig
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import later.await
import pimonitor.client.businesses.BusinessesService
import presenters.feedbacks.Feedback
import viewmodel.ViewModel

class BusinessDetailsViewModel(
    private val config: UIScopeConfig<BusinessesService>
) : ViewModel<Intent, State>(State(), config.viewModel) {
    private val service get() = config.service
    override fun CoroutineScope.execute(i: Intent): Any = when (i) {
        is Intent.LoadOperationDashboard -> loadOperationDashboard(i)
    }

    private fun CoroutineScope.loadOperationDashboard(i: Intent.LoadOperationDashboard) = launch {
        val state = ui.value
        flow {
            emit(state.copy(status = Feedback.Loading("Loading operational dashboard, please wait . . .")))
            val operationalDashboard = service.operationalDashboard(i.businessId).await()
            emit(state.copy(status = Feedback.None, operationDashboard = operationalDashboard))
        }.catch {
            emit(state.copy(status = Feedback.Failure(it)))
            delay(config.viewModel.recoveryTime)
            emit(state.copy(status = Feedback.None))
        }.collect{
            ui.value = it
        }
    }
}