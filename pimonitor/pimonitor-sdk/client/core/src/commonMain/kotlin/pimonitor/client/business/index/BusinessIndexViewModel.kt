package pimonitor.client.business.index

import bitframe.client.UIScopeConfig
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import later.await
import pimonitor.client.businesses.BusinessesService
import pimonitor.core.businesses.MonitoredBusinessBasicInfo
import presenters.cases.Mission
import presenters.cases.State
import presenters.intents.IndexIntent
import viewmodel.ViewModel

class BusinessIndexViewModel(
    private val config: UIScopeConfig<BusinessesService>
) : ViewModel<IndexIntent, Mission<MonitoredBusinessBasicInfo>>(LOADING_STATE, config.viewModel) {

    companion object {
        private val LOADING_STATE = Mission.Loading("Loading business information, please wait . . .!")
    }

    private val service get() = config.service

    override fun CoroutineScope.execute(i: IndexIntent): Any = when (i) {
        is IndexIntent.Load -> loadBusiness(i)
    }

    private fun CoroutineScope.loadBusiness(i: IndexIntent.Load) = launch {
        flow {
            emit(LOADING_STATE)
            emit(Mission.Success(service.load(i.uid).await()))
        }.catch {
            emit(Mission.Failure(it) {
                onRetry { post(i) }
            })
        }.collect {
            ui.value = it
        }
    }
}