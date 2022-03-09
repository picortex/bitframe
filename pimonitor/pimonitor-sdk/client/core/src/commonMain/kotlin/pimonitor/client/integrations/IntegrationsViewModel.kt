package pimonitor.client.integrations

import bitframe.client.UIScopeConfig
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import later.await
import pimonitor.client.PiMonitorApi
import presenters.feedbacks.Feedback
import viewmodel.ViewModel
import pimonitor.client.integrations.IntegrationsIntent as Intent
import pimonitor.client.integrations.IntegrationsState as State

class IntegrationsViewModel(
    private val config: UIScopeConfig<PiMonitorApi>
) : ViewModel<Intent, State>(State(), config.viewModel) {
    val api get() = config.service
    override fun CoroutineScope.execute(i: Intent) = when (i) {
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
            dialog = IntegrationsDialog.sageIntegrationDialog {
                onCancel { post(Intent.ExitDialog) }
                onSubmit { params -> post(Intent.SubmitSageOneInviteForm(params)) }
            }
        )
    }

    private fun CoroutineScope.loadIntegrationInfo(i: Intent.LoadIntegrationInfo) = launch {
        val state = ui.value.copy(dialog = null)
        flow {
            emit(state.copy(status = Feedback.Loading("Preparing invite information, please wait . . .")))
            logger.warn("Please, provide concrete info about the one requesting integration")
            delay(config.viewModel.transitionTime)
            emit(state.copy(status = Feedback.None, info = "PiMonitor will be used to share your information"))
        }.collect {
            ui.value = it
        }
    }

    private fun CoroutineScope.acceptSageOneInvite(i: Intent.SubmitSageOneInviteForm) = launch {
        val state = ui.value.copy(dialog = null)
        flow {
            emit(state.copy(status = Feedback.Loading("Saving your integration information, please wait . . .")))
            api.sage.acceptInvite(i.params).await()
            emit(state.copy(status = Feedback.Success("Successfully integrated with sage")))
        }.collect {
            ui.value = state
        }
    }
}