package pimonitor.client.business.financials

import bitframe.client.UIScopeConfig
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import later.await
import pimonitor.client.businesses.BusinessesService
import pimonitor.core.invites.InfoResults
import presenters.cases.Feedback
import viewmodel.ViewModel
import pimonitor.client.business.financials.BusinessFinancialIntent as Intent
import pimonitor.client.business.financials.BusinessFinancialsState as State

class BusinessFinancialsViewModel(
    private val config: UIScopeConfig<BusinessesService>
) : ViewModel<Intent, State>(State(), config.viewModel) {

    private val service get() = config.service

    override fun CoroutineScope.execute(i: Intent): Any = when (i) {
        is Intent.LoadAvailableReports -> loadAvailableReports(i)
        is Intent.LoadBalanceSheet -> loadBalanceSheet(i)
        is Intent.LoadIncomeStatement -> loadIncomeStatement(i)
    }

    private fun CoroutineScope.loadAvailableReports(i: Intent.LoadAvailableReports) = launch {
        val state = ui.value
        flow {
            emit(state.copy(status = Feedback.Loading(State.DEFAULT_LOADING_MESSAGE)))
            val results = service.availableReports(i.businessId).await()
            val reports = results.reports
            if (reports.isEmpty()) {
                emit(state.copy(status = Feedback.None, availableReports = InfoResults.NotShared("Ooops, looks like ${results.business.name} hasn't shared any reports with you just yet")))
            } else {
                emit(state.copy(status = Feedback.None, availableReports = InfoResults.Shared(reports)))
            }
        }.catch {
            emit(state.copy(status = Feedback.Failure(it) {
                onRetry { post(i) }
            }))
        }.collect {
            ui.value = it
        }
    }

    private suspend fun Flow<State>.catchAndCollectToUI(state: State, i: Intent) = catch {
        emit(state.copy(status = Feedback.Failure(it) {
            onRetry { post(i) }
        }))
    }.collect {
        ui.value = it
    }

    private fun CoroutineScope.loadBalanceSheet(i: Intent.LoadBalanceSheet) = launch {
        val state = ui.value
        flow {
            emit(state.copy(status = Feedback.Loading("Loading balance sheet, please wait . . .")))
            val sharedInfo = service.balanceSheet(i.businessId).await() as InfoResults.Shared
            emit(state.copy(status = Feedback.None, report = sharedInfo.data))
        }.catchAndCollectToUI(state, i)
    }

    private fun CoroutineScope.loadIncomeStatement(i: Intent.LoadIncomeStatement) = launch {
        val state = ui.value
        flow {
            emit(state.copy(status = Feedback.Loading("Loading income statement, please wait . . .")))
            val sharedInfo = service.incomeStatement(i.businessId).await() as InfoResults.Shared
            emit(state.copy(status = Feedback.None, report = sharedInfo.data))
        }.catchAndCollectToUI(state, i)
    }
}