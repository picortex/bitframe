package pimonitor.client.businesses

import bitframe.client.UIScopeConfig
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import later.await
import live.WatchMode
import live.Watcher
import pimonitor.client.businesses.BusinessesDialogContent.captureInvestmentDialog
import pimonitor.client.businesses.BusinessesIntent.*
import pimonitor.client.businesses.BusinessesDialogContent.createBusinessDialog
import pimonitor.client.businesses.BusinessesDialogContent.deleteManyDialog
import pimonitor.client.businesses.BusinessesDialogContent.deleteSingleDialog
import pimonitor.client.businesses.BusinessesDialogContent.interveneDialog
import pimonitor.client.businesses.BusinessesDialogContent.inviteToShareDialog
import pimonitor.client.businesses.BusinessesDialogContent.updateInvestmentDialog
import pimonitor.core.businesses.DASHBOARD
import pimonitor.core.businesses.models.MonitoredBusinessSummary
import presenters.feedbacks.Feedback
import presenters.table.TableState
import presenters.table.builders.tableOf
import viewmodel.ViewModel
import pimonitor.client.businesses.BusinessesIntent as Intent
import pimonitor.client.businesses.BusinessesState as State

class BusinessesViewModel(
    private val config: UIScopeConfig<BusinessesService>
) : ViewModel<Intent, State>(State(), config.viewModel) {

    private val service: BusinessesService = config.service

    override fun CoroutineScope.execute(i: Intent): Any = when (i) {
        LoadBusinesses -> loadBusiness()
        ShowCreateBusinessForm -> ui.value = ui.value.copy(
            dialog = createBusinessDialog { onCancel { start(ExitDialog) } }
        )
        is ShowInviteToShareReportsForm -> ui.value = ui.value.copy(
            dialog = inviteToShareDialog(i.monitored) { onCancel { start(ExitDialog) } }
        )
        is ShowInterveneForm -> ui.value = ui.value.copy(
            dialog = interveneDialog(i.monitored) {
                onCancel { start(ExitDialog) }
                onSubmit {  }
            }
        )
        is ShowCaptureInvestmentForm -> ui.value = ui.value.copy(
            dialog = captureInvestmentDialog(i.monitored) { onCancel { start(ExitDialog) } }
        )
        is ShowUpdateInvestmentForm -> ui.value = ui.value.copy(
            dialog = updateInvestmentDialog(i.monitored) { onCancel { start(ExitDialog) } }
        )
        is ShowDeleteMultipleConfirmationDialog -> ui.value = ui.value.copy(
            dialog = deleteManyDialog(i.data) {
                onCancel { start(ExitDialog) }
                onConfirm { start(DeleteAll(i.data.map { it.data }.toTypedArray())) }
            }
        )
        is ShowDeleteSingleConfirmationDialog -> ui.value = ui.value.copy(
            dialog = deleteSingleDialog(i.monitored) {
                onCancel { start(ExitDialog) }
                onConfirm { start(Delete(i.monitored)) }
            }
        )
        ExitDialog -> exitDialog()
        is Delete -> delete(i)
        is DeleteAll -> deleteAll(i)
    }

    private fun CoroutineScope.updateInvestment(i: ShowUpdateInvestmentForm) = launch {
        val state = ui.value
        flow {
            emit(state.copy(status = Feedback.Loading("Capturing investment")))
            error("Implement update investment for ${i.monitored}")
        }.catchAndCollectToUI(state)
    }

    private fun CoroutineScope.intervene(i: ShowInterveneForm) = launch {
        val state = ui.value
        flow {
            emit(state.copy(status = Feedback.Loading("Intervening")))
            error("Implement intervene for ${i.monitored.name}")
        }.catchAndCollectToUI(state)
    }

    private fun CoroutineScope.deleteAll(i: DeleteAll) = launch {
        val state = ui.value
        flow {
            emit(state.copy(status = Feedback.Loading("Deleting ${i.data.size} businesses")))
            service.delete(*i.data.map { it.uid }.toTypedArray()).await()
            emit(state.copy(status = Feedback.Success("${i.data.size} businesses deleted successfully"), dialog = null))
        }.catchAndCollectToUI(state)
        delay(config.viewModel.transitionTime)
        start(LoadBusinesses)
    }

    private fun CoroutineScope.delete(i: Delete) = launch {
        val state = ui.value
        flow {
            emit(state.copy(status = Feedback.Loading("Deleting ${i.monitored.name}"), dialog = null))
            service.delete(i.monitored.uid).await()
            emit(state.copy(status = Feedback.Success("Successfully delete ${i.monitored.name}"), dialog = null))
        }.catchAndCollectToUI(state)
        delay(config.viewModel.transitionTime)
        start(LoadBusinesses)
    }

    private fun CoroutineScope.captureInvestment(i: ShowCaptureInvestmentForm) = launch {
        val state = ui.value
        flow {
            emit(state.copy(status = Feedback.Loading("Capturing investment")))
            error("Implement capture investment for ${i.monitored}")
        }.catchAndCollectToUI(state)
    }

    private fun exitDialog() {
        val state = ui.value
        if (state.dialog != null) {
            ui.value = state.copy(dialog = null)
        }
    }

    private suspend fun Flow<State>.catchAndCollectToUI(state: State) = catch {
        emit(state.copy(status = Feedback.Failure(it), dialog = null))
        delay(config.viewModel.recoveryTime)
        emit(state.copy(status = Feedback.None, dialog = null))
    }.collect {
        ui.value = it
    }

    private fun CoroutineScope.loadBusiness() = launch {
        val state = ui.value
        flow {
            val phase1 = state.copy(
                status = Feedback.Loading("Loading your businesses, please wait . . ."),
                dialog = null
            )
            emit(phase1)
            val phase2 = state.copy(
                status = Feedback.None,
                table = businessTable(service.all().await()),
                dialog = null
            )
            emit(phase2)
        }.catchAndCollectToUI(state)
    }

    private fun CoroutineScope.businessTable(data: List<MonitoredBusinessSummary>) = tableOf(data) {
        primaryAction("Add Business") { start(ShowCreateBusinessForm) }
        singleAction("Intervene") { start(ShowInterveneForm(it.data)) }
        singleAction("Capture Investment") { start(ShowCaptureInvestmentForm(it.data)) }
        singleAction("Update Investment") { start(ShowUpdateInvestmentForm(it.data)) }
        singleAction("Delete") { start(ShowDeleteSingleConfirmationDialog(it.data)) }
        multiAction("Delete All") { start(ShowDeleteMultipleConfirmationDialog(it)) }
        selectable()
        column("Name") { it.data.name }
        column("Reporting") {
            when (val business = it.data) {
                is MonitoredBusinessSummary.ConnectedDashboard -> business.dashboard
                is MonitoredBusinessSummary.UnConnectedDashboard -> DASHBOARD.NONE
            }
        }
        column("Revenue") { "" }
        column("Expenses") { "" }
        column("GP") { "" }
        column("Velocity") { "" }
        column("NCF") { "" }
        column("V/day") { "" }
        actionsColumn("Actions") {
            action("Invite to share reports") { start(ShowInviteToShareReportsForm(it.data)) }
            action("Intervene") { start(ShowInterveneForm(it.data)) }
            action("Capture Investment") { start(ShowCaptureInvestmentForm(it.data)) }
            action("Update Investment") { start(ShowUpdateInvestmentForm(it.data)) }
            action("Delete") { start(ShowDeleteSingleConfirmationDialog(it.data)) }
        }
    }
}