package pimonitor.client.investment.index

import bitframe.client.UIScopeConfig
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import later.await
import pimonitor.client.investments.InvestmentsService
import pimonitor.core.investments.InvestmentSummary
import presenters.cases.Mission
import presenters.intents.IndexIntent
import viewmodel.ViewModel

class InvestmentIndexViewModel(
    val config: UIScopeConfig<InvestmentsService>
) : ViewModel<IndexIntent, Mission<InvestmentSummary>>(DEFAULT_LOADING_STATE, config.viewModel) {
    private val service get() = config.service

    companion object {
        private val DEFAULT_LOADING_STATE = Mission.Loading("Loading investment, please wait. . .")
    }

    override fun CoroutineScope.execute(i: IndexIntent): Any = when (i) {
        is IndexIntent.Load -> loadInvestment(i)
    }

    private fun CoroutineScope.loadInvestment(i: IndexIntent.Load) = launch {
        flow {
            emit(DEFAULT_LOADING_STATE)
            val investment = service.load(i.uid).await()
            emit(Mission.Success(investment))
        }.catch {
            emit(Mission.Failure(it) {
                onRetry { post(i) }
            })
        }.collect {
            ui.value = it
        }
    }
}