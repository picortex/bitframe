package pimonitor.evaluation.businesses.forms

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import later.await
import live.value
import pimonitor.PiMonitorViewModelConfig
import pimonitor.client.evaluation.businesses.BusinessesService
import pimonitor.client.monitors.MonitorsService
import viewmodel.ViewModel
import viewmodel.ViewModelConfig
import pimonitor.evaluation.businesses.forms.CreateBusinessIntent as Intent
import pimonitor.evaluation.businesses.forms.CreateBusinessState as State

class CreateBusinessViewModel(
    config: PiMonitorViewModelConfig
) : ViewModel<Intent, State>(State.Loading("Preparing form, please wait . . ."), config) {
    private val monitorService: MonitorsService = config.service.monitors
    private val businessService: BusinessesService = config.service.businesses
    private val recoverTime = config.recoveryTime
    override fun CoroutineScope.execute(i: Intent): Any = when (i) {
        is Intent.ShowForm -> when (val uid = i.inviteId) {
            null -> showAddBusinessForm()
            else -> showInviteForm(uid)
        }
        is Intent.SubmitForm -> submitForm(i)
    }

    private fun CoroutineScope.submitForm(i: Intent.SubmitForm) = launch {
        val state = ui.value
        flow {
            emit(State.Loading("Adding business, please wait . . ."))
            businessService.create(i.params).await()
            emit(State.Success("Business Added"))
        }.catch {
            emit(State.Failure(it))
            delay(recoverTime)
            emit(state.copy(i))
        }.collect { ui.value = it }
    }

    private fun showAddBusinessForm() {
        ui.value = State.Form(AddBusinessFormFields(), null)
    }

    private fun CoroutineScope.showInviteForm(uid: String) = launch {
        flow {
            emit(State.Loading("Fetching invite information, please wait . . ."))
            val monitor = monitorService.load(uid).await() ?: throw RuntimeException("Failed to load inviter(uid=$uid) information")
            val fields = InviteBusinessFormFields(monitor)
            emit(State.Form(fields, null))
        }.catch {
            emit(State.Failure(it))
        }.collect { ui.value = it }
    }
}