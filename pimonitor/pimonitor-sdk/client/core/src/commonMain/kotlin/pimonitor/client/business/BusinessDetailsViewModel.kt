package pimonitor.client.business

import bitframe.client.UIScopeConfig
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
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
        is Intent.LoadIncomeStatement -> loadIncomeStatement(i)
    }

    private suspend fun Flow<State>.catchAndCollectToUI(state: State) = catch {
        emit(state.copy(status = Feedback.Failure(it)))
        delay(config.viewModel.recoveryTime)
        emit(state.copy(status = Feedback.None))
    }.collect {
        ui.value = it
    }

    private fun CoroutineScope.loadIncomeStatement(i: Intent.LoadIncomeStatement) = launch {
        val state = ui.value
        flow {
            emit(state.copy(status = Feedback.Loading("Loading income statement, please wait . . .")))
            val pnl = service.incomeStatement(i.businessId).await()
            emit(state.copy(status = Feedback.None, incomeStatement = pnl))
        }.catchAndCollectToUI(state)
    }

    private fun CoroutineScope.loadOperationDashboard(i: Intent.LoadOperationDashboard) = launch {
        val state = ui.value
        flow {
            emit(state.copy(status = Feedback.Loading("Loading operational dashboard, please wait . . .")))
            val operationalDashboard = service.operationalDashboard(i.businessId).await()
            emit(state.copy(status = Feedback.None, operationDashboard = operationalDashboard))
        }.catchAndCollectToUI(state)
    }
}