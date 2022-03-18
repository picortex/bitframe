package pimonitor.client.business

import bitframe.client.UIScopeConfig
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import later.await
import pimonitor.client.businesses.BusinessesService
import pimonitor.core.businesses.MonitoredBusinessBasicInfo
import presenters.state.State
import viewmodel.ViewModel

class BusinessDetailsViewModel(
    private val config: UIScopeConfig<BusinessesService>
) : ViewModel<BusinessDetailsIntent, State<MonitoredBusinessBasicInfo>>(State.Loading("Loading business information, please wait. . ."), config.viewModel) {

    private val service get() = config.service

    override fun CoroutineScope.execute(i: BusinessDetailsIntent): Any = when (i) {
        is BusinessDetailsIntent.LoadBusiness -> loadBusiness(i)
    }

    private fun CoroutineScope.loadBusiness(i: BusinessDetailsIntent.LoadBusiness) = launch {
        flow {
            emit(State.Loading("Loading business information, please wait . . .!"))
            emit(State.Content(service.load(i.businessId).await()))
        }.catch {
            emit(State.Failure(it) {
                onRetry { post(i) }
            })
        }.collect {
            ui.value = it
        }
    }
}