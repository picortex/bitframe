package pimonitor.client.business.info

import bitframe.client.UIScopeConfig
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import later.await
import pimonitor.client.PiMonitorApi
import pimonitor.client.business.info.BusinessInfoIntent.*
import pimonitor.client.business.info.forms.BusinessInfoEditForm
import pimonitor.client.utils.live.exitDialog
import pimonitor.client.utils.live.update
import pimonitor.core.business.info.params.BusinessInfoRawFormParams
import pimonitor.core.business.info.params.toValidatedParams
import pimonitor.core.businesses.MonitoredBusinessBasicInfo
import presenters.cases.GenericState
import presenters.modal.dialog
import viewmodel.ViewModel

class BusinessInfoViewModel(
    val config: UIScopeConfig<PiMonitorApi>
) : ViewModel<BusinessInfoIntent, GenericState<MonitoredBusinessBasicInfo>>(DEFAULT_LOADING_STATE, config.viewModel) {

    companion object {
        val DEFAULT_LOADING_STATE = GenericState.Loading("Loading business info, please wait. . .")
    }

    val api get() = config.service
    override fun CoroutineScope.execute(i: BusinessInfoIntent): Any = when (i) {
        is LoadInfo -> loadInfo(i)
        is ShowEditForm -> showEditForm(i)
        is SendEditForm -> sendEditForm(i)
    }

    private fun CoroutineScope.sendEditForm(i: SendEditForm) = launch {
        flow {
            emit(GenericState.Loading("Updating ${i.params.name}, please wait . . ."))
            val params = i.params.toValidatedParams(businessId = i.business.uid)
            val updated = api.businesses.update(params).await()
            emit(GenericState.Content(updated))
        }.catch {
            emit(GenericState.Failure(it) {
                onCancel { post(ShowEditForm(i.business, i.params)) }
                onRetry { post(i) }
            })
        }.collect {
            ui.update { it }
        }
    }

    private fun CoroutineScope.showEditForm(i: ShowEditForm) = launch {
        val state = ui.value
        flow {
            val form = BusinessInfoEditForm(i.params, i.business) {
                onCancel { ui.exitDialog() }
                onSubmit("Save Changes") { post(SendEditForm(i.business, it)) }
            }
            emit(state.copy(dialog = dialog(form)))
        }.catch {
            emit(GenericState.Failure(it) {
                onGoBack { ui.exitDialog() }
                onRetry { post(i) }
            })
        }.collect {
            ui.update { it }
        }
    }

    private fun CoroutineScope.loadInfo(i: LoadInfo) = launch {
        flow {
            emit(DEFAULT_LOADING_STATE)
            emit(GenericState.Content(api.businesses.load(i.businessId).await()))
        }.catch {
            emit(GenericState.Failure(it) {
                onRetry { post(i) }
            })
        }.collect {
            ui.value = it
        }
    }
}