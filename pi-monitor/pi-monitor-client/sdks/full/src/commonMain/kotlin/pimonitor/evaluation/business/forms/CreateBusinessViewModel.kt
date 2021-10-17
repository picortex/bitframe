package pimonitor.evaluation.business.forms

import bitframe.authentication.users.Contacts
import bitframe.authentication.users.UserRef
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import later.await
import pimonitor.evaluation.businesses.BusinessesService
import pimonitor.monitors.MonitorsService
import viewmodel.ViewModel
import pimonitor.evaluation.business.forms.CreateBusinessIntent as Intent
import pimonitor.evaluation.business.forms.CreateBusinessState as State

class CreateBusinessViewModel(
    val monitorService: MonitorsService,
    val businessService: BusinessesService
) : ViewModel<Intent, State>(State.Loading("Preparing form, please wait . . .")) {
    override fun CoroutineScope.execute(i: Intent): Any = when (i) {
        is Intent.ShowForm -> when (val uid = i.inviteId) {
            null -> showAddBusinessForm()
            else -> showInviteForm(uid)
        }
    }

    private fun showAddBusinessForm() {
        ui.value = State.Form(AddBusinessFormFields(), null)
    }

    private fun CoroutineScope.showInviteForm(uid: String) = launch {
        flow {
            emit(State.Loading("Fetching invite information, please wait . . ."))
            val monitor = monitorService.monitor(UserRef(uid = uid, name = "", tag = "", contacts = Contacts.None, null)).await()
            val fields = InviteBusinessFormFields(monitor)
            emit(State.Form(fields,null))
        }.catch {
            emit(State.Failure(it))
        }.collect { ui.value = it }
    }
}