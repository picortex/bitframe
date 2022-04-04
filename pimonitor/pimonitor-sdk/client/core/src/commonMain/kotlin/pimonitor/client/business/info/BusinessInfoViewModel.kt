package pimonitor.client.business.info

import bitframe.client.UIScopeConfig
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import later.await
import pimonitor.client.PiMonitorApi
import pimonitor.client.business.BusinessDetailsIntent
import pimonitor.client.business.info.BusinessInfoIntent.*
import pimonitor.client.business.info.forms.BusinessInfoEditForm
import pimonitor.core.business.info.params.toValidatedParams
import pimonitor.core.businesses.MonitoredBusinessBasicInfo
import presenters.cases.State
import presenters.modal.dialog
import viewmodel.ViewModel

class BusinessInfoViewModel(
    val config: UIScopeConfig<PiMonitorApi>
) : ViewModel<BusinessInfoIntent, State<MonitoredBusinessBasicInfo>>(State.Loading("Fetching info . . ."), config.viewModel) {
    val api get() = config.service
    override fun CoroutineScope.execute(i: BusinessInfoIntent): Any = when (i) {
        is ExitDialog -> exitDialog()
        is LoadInfo -> loadInfo(i)
        is ShowEditForm -> showEditForm(i)
        is SendEditForm -> sendEditForm(i)
    }

    private fun exitDialog() {
        val content = ui.value as? State.Content
        if (content != null) {
            ui.value = content.copy(dialog = null)
        }
    }

    private fun CoroutineScope.sendEditForm(i: SendEditForm) = launch {
        val state = ui.value as State.Content
        flow {
            val content = ui.value as? State.Content ?: error("Make sure you are already viewing the info to be able to submit the form")
            emit(State.Loading("Saving changes, please wait . . ."))
            val params = i.params.toValidatedParams(businessId = content.value.uid)
            val updated = api.businesses.update(params).await()
            emit(State.Content(updated))
        }.catch {
            emit(State.Failure(it))
            delay(config.viewModel.recoveryTime)
            emit(state)
        }.collect {
            ui.value = it
        }
    }

    private fun CoroutineScope.showEditForm(i: ShowEditForm) = launch {
        flow {
            val content = ui.value as? State.Content ?: error("Make sure you are already viewing the info to be able to edit it")
            val form = BusinessInfoEditForm(content.value) {
                onCancel { post(ExitDialog) }
                onSubmit("Save Changes") { post(SendEditForm(it)) }
            }
            emit(content.copy(dialog = dialog(form)))
        }.catch {
            emit(State.Failure(it) {
                onGoBack { post(ExitDialog) }
                onRetry { post(i) }
            })
        }.collect {
            ui.value = it
        }
    }

    private fun CoroutineScope.loadInfo(i: LoadInfo) = launch {
        flow {
            emit(State.Loading("Loading business info, please wait . . ."))
            emit(State.Content(api.businesses.load(i.businessId).await()))
        }.catch {
            emit(State.Failure(it) {
                onRetry { post(i) }
            })
        }.collect {
            ui.value = it
        }
    }
}