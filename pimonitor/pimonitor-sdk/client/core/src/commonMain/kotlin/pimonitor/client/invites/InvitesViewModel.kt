package pimonitor.client.invites

import bitframe.client.UIScopeConfig
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import later.await
import pimonitor.client.PiMonitorApi
import pimonitor.core.sage.copy
import presenters.feedbacks.Feedback
import viewmodel.ViewModel
import pimonitor.client.invites.InvitesIntent as Intent
import pimonitor.client.invites.InvitesState as State

class InvitesViewModel(
    private val config: UIScopeConfig<PiMonitorApi>
) : ViewModel<Intent, State>(State(), config.viewModel) {
    val api get() = config.service
    override fun CoroutineScope.execute(i: Intent): Any = when (i) {
        is Intent.ExitDialog -> exitDialog()
        is Intent.LoadIntegrationInfo -> loadIntegrationInfo(i)
        is Intent.ShowSageOneInviteForm -> showSageOneInviteForm()
        is Intent.SubmitSageOneInviteForm -> acceptSageOneInvite(i)
    }

    private fun exitDialog() {
        ui.value = ui.value.copy(dialog = null)
    }

    private fun showSageOneInviteForm() {
        ui.value = ui.value.copy(
            status = Feedback.None,
            dialog = InvitesDialog.sageIntegrationDialog {
                onCancel { post(Intent.ExitDialog) }
                onSubmit { params -> post(Intent.SubmitSageOneInviteForm(params)) }
            }
        )
    }

    private fun CoroutineScope.loadIntegrationInfo(i: Intent.LoadIntegrationInfo) = launch {
        val state = ui.value.copy(dialog = null)
        flow {
            emit(state.copy(status = Feedback.Loading("Preparing invite information, please wait . . .")))
            val invite = api.invites.load(i.inviteId).await()
            emit(state.copy(status = Feedback.None, title = "${invite.invitorName} is requesting you to share your reports", info = invite))
        }.catch {
            emit(state.copy(status = Feedback.Failure(it)))
        }.collect {
            ui.value = it
        }
    }

    private fun CoroutineScope.acceptSageOneInvite(i: Intent.SubmitSageOneInviteForm) = launch {
        val state = ui.value.copy(dialog = null)
        flow {
            val inviteId = state.info?.inviteId ?: error("Couldn't get invite id. Make sure you have loaded the viewModel with a new invite id")
            emit(state.copy(status = Feedback.Loading("Saving your integration information, please wait . . .")))
            api.invites.accept(i.params.copy(inviteId)).await()
            emit(state.copy(status = Feedback.Success("Successfully integrated with sage")))
            delay(config.viewModel.transitionTime)
            emit(state.copy(status = Feedback.None))
        }.collect {
            ui.value = state
        }
    }
}