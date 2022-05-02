package pimonitor.client.utils.disbursable.index

import bitframe.client.UIScopeConfig
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import later.await
import pimonitor.client.utils.disbursables.DisbursableService
import pimonitor.core.utils.disbursables.DisbursableSummary
import presenters.cases.MissionState
import presenters.cases.copy
import presenters.intents.IndexIntent
import viewmodel.ViewModel

class DisbursableIndexViewModel<out DS : DisbursableSummary>(
    val config: UIScopeConfig<DisbursableService<*, DS>>
) : ViewModel<IndexIntent, MissionState<@UnsafeVariance DS>>(
    MissionState.Loading(DEFAULT_LOADING_MESSAGE), config.viewModel
) {
    private val service get() = config.service

    companion object {
        private val DEFAULT_LOADING_MESSAGE = "Loading investment, please wait. . ."
    }

    override fun CoroutineScope.execute(i: IndexIntent): Any = when (i) {
        is IndexIntent.Load -> loadInvestment(i)
    }

    private fun CoroutineScope.loadInvestment(i: IndexIntent.Load) = launch {
        val state = ui.value
        flow {
            emit(state.copy(DEFAULT_LOADING_MESSAGE))
            emit(state.copy(service.load(i.uid).await()))
        }.catch {
            emit(state.copy(it) {
                onRetry { post(i) }
            })
        }.collect {
            ui.value = it
        }
    }
}