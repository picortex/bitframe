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
import presenters.cases.State
import viewmodel.ViewModel
import pimonitor.client.business.financials.BusinessFinancialsContent as Content
import pimonitor.client.business.financials.BusinessFinancialIntent as Intent

class BusinessFinancialsViewModel(
    private val config: UIScopeConfig<BusinessesService>
) : ViewModel<Intent, State<Content>>(State.Loading(DEFAULT_LOADING_MESSAGE)) {

    private val service get() = config.service

    companion object {
        const val DEFAULT_LOADING_MESSAGE = "Loading available reports, please wait . . ."
    }

    override fun CoroutineScope.execute(i: Intent): Any = when (i) {
        is Intent.LoadAvailableReports -> loadAvailableReports(i)
        is Intent.LoadBalanceSheet -> loadBalanceSheet(i)
        is Intent.LoadIncomeStatement -> loadIncomeStatement(i)
    }

    private fun CoroutineScope.loadAvailableReports(i: Intent.LoadAvailableReports) = launch {
        flow {
            emit(State.Loading(DEFAULT_LOADING_MESSAGE))
            val results = service.availableReports(i.businessId).await()
            emit(State.Content(Content.None(results.reports)))
        }.catch {
            emit(State.Failure(it) {
                onRetry { post(i) }
            })
        }.collect {
            ui.value = it
        }
    }

    private suspend fun Flow<State<Content>>.catchAndCollectToUI(i: Intent) = catch {
        emit(State.Failure(it) {
            onRetry { post(i) }
        })
    }.collect {
        ui.value = it
    }

    private fun CoroutineScope.loadBalanceSheet(i: Intent.LoadBalanceSheet) = launch {
        flow {
            val state = (ui.value as? State.Content)?.value ?: error("Can't load balance sheet before checking all available reports")
            emit(State.Loading("Loading balance sheet, please wait . . ."))
            val sharedBalanceSheet = service.balanceSheet(i.businessId).await() as InfoResults.Shared
            emit(State.Content(Content.Report(state.availableReports, sharedBalanceSheet.data)))
        }.catchAndCollectToUI(i)
    }

    private fun CoroutineScope.loadIncomeStatement(i: Intent.LoadIncomeStatement) = launch {
        flow {
            val state = (ui.value as? State.Content)?.value ?: error("Can't load income statement before checking all available reports")
            emit(State.Loading("Loading income statement, please wait . . ."))
            val sharedIncomeStatement = service.incomeStatement(i.businessId).await() as InfoResults.Shared
            emit(State.Content(Content.Report(state.availableReports, sharedIncomeStatement.data)))
        }.catchAndCollectToUI(i)
    }
}