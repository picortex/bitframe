package pimonitor.client.utils.disbursable.index

import bitframe.client.UIScopeConfig
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import later.await
import pimonitor.client.utils.disbursables.DisbursableService
import pimonitor.client.utils.disbursables.DisbursablesViewModel
import pimonitor.core.utils.disbursables.Disbursable
import pimonitor.core.utils.disbursables.DisbursableSummary
import presenters.cases.Mission
import presenters.intents.IndexIntent
import viewmodel.ViewModel

class DisbursableIndexViewModel<out DS : DisbursableSummary>(
    val config: UIScopeConfig<DisbursableService<*, DS>>
) : ViewModel<IndexIntent, Mission<@UnsafeVariance DS>>(DEFAULT_LOADING_STATE, config.viewModel) {
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
            val disbursable = service.load(i.uid).await()
            emit(Mission.Success(disbursable))
        }.catch {
            emit(Mission.Failure(it) {
                onRetry { post(i) }
            })
        }.collect {
            ui.value = it
        }
    }
}