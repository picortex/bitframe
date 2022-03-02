package pimonitor.client.businesses

import bitframe.client.UIScopeConfig
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import later.await
import logging.console
import pimonitor.client.businesses.BusinessesDialogContent.captureInvestmentDialog
import pimonitor.client.businesses.BusinessesDialogContent.createBusinessDialog
import pimonitor.client.businesses.BusinessesDialogContent.deleteManyDialog
import pimonitor.client.businesses.BusinessesDialogContent.deleteSingleDialog
import pimonitor.client.businesses.BusinessesDialogContent.interveneDialog
import pimonitor.client.businesses.BusinessesDialogContent.inviteToShareDialog
import pimonitor.client.businesses.BusinessesIntent.*
import pimonitor.core.businesses.DASHBOARD
import pimonitor.core.businesses.models.MonitoredBusinessSummary
import pimonitor.core.businesses.params.CreateMonitoredBusinessRawParams
import pimonitor.core.businesses.params.InviteToShareReportsRawParams
import presenters.feedbacks.Feedback
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
            dialog = createBusinessDialog {
                onCancel { post(ExitDialog) }
                onSubmit { params: CreateMonitoredBusinessRawParams ->
                }
            }
        )
        is SubmitCreateBusinessForm -> submitCreateBusinessForm(i)
        is ShowInviteToShareReportsForm -> ui.value = ui.value.copy(
            dialog = inviteToShareDialog(i.monitored.name) {
                onCancel { post(ExitDialog) }
                onSubmit { params: Unit -> TODO() }
            }
        )
        is SubmitInviteToShareReportsForm -> submitInviteToShare(i)
        is ShowInterveneForm -> ui.value = ui.value.copy(
            dialog = interveneDialog(i.monitored) {
                onCancel { post(ExitDialog) }
                onSubmit { params: Unit -> TODO() }
            }
        )
        is ShowCaptureInvestmentForm -> ui.value = ui.value.copy(
            dialog = captureInvestmentDialog(i.monitored) {
                onCancel { post(ExitDialog) }
                onSubmit { params: Unit -> TODO() }
            }
        )
        is ShowDeleteMultipleConfirmationDialog -> ui.value = ui.value.copy(
            dialog = deleteManyDialog(i.data) {
                onCancel { post(ExitDialog) }
                onConfirm { post(DeleteAll(i.data.map { it.data }.toTypedArray())) }
            }
        )
        is ShowDeleteSingleConfirmationDialog -> ui.value = ui.value.copy(
            dialog = deleteSingleDialog(i.monitored) {
                onCancel { post(ExitDialog) }
                onConfirm { post(Delete(i.monitored)) }
            }
        )

        ExitDialog -> exitDialog()
        is Delete -> delete(i)
        is DeleteAll -> deleteAll(i)
    }

    private fun CoroutineScope.submitInviteToShare(i: SubmitInviteToShareReportsForm) = launch {
        val state = ui.value
        flow {
            emit(state.copy(status = Feedback.Success("Invite sent")))
            logger.log("Check to see why messages are not being sent")
            delay(config.viewModel.transitionTime)
            emit(state.copy(status = Feedback.None, dialog = null))
        }.catchAndCollectToUI(ui.value)
    }

    private fun CoroutineScope.submitCreateBusinessForm(i: SubmitCreateBusinessForm) = launch {
        val state = ui.value
        flow {
            emit(state.copy(status = Feedback.Loading("Adding ${i.params.businessName}, please wait . . ."), dialog = null))
            service.create(i.params).await()
            emit(state.copy(status = Feedback.Success("${i.params.businessName} has successfully been added"), dialog = null))
            if (i.params.sendInvite) {
                val phase3 = state.copy(
                    status = Feedback.None,
                    dialog = inviteToShareDialog(i.params) {
                        onCancel { post(ExitDialog) }
                        onSubmit { params: InviteToShareReportsRawParams -> post(SubmitInviteToShareReportsForm(params)) }
                    }
                )
                emit(phase3)
            } else {
                delay(config.viewModel.transitionTime)
                emit(state.copy(status = Feedback.Loading("Loading your businesses, please wait . . ."), dialog = null))
                emit(state.copy(status = Feedback.None, table = businessTable(service.all().await()), dialog = null))
            }
        }.catchAndCollectToUI(state)
    }

    private fun CoroutineScope.intervene(i: ShowInterveneForm) = launch {
        val state = ui.value
        flow {
            emit(state.copy(status = Feedback.Loading("Intervening"), dialog = null))
            error("Implement intervene for ${i.monitored.name}")
        }.catchAndCollectToUI(state)
    }

    private fun CoroutineScope.deleteAll(i: DeleteAll) = launch {
        val state = ui.value
        flow {
            emit(state.copy(status = Feedback.Loading("Deleting ${i.data.size} businesses")))
            service.delete(*i.data.map { it.uid }.toTypedArray()).await()
            emit(state.copy(status = Feedback.Success("${i.data.size} businesses deleted successfully, Loading remaining businesses . . ."), dialog = null))
            val phase = state.copy(
                status = Feedback.None,
                table = businessTable(service.all().await()),
                dialog = null
            )
            emit(phase)
        }.catchAndCollectToUI(state)
    }

    private fun CoroutineScope.delete(i: Delete) = launch {
        val state = ui.value
        flow {
            emit(state.copy(status = Feedback.Loading("Deleting ${i.monitored.name}"), dialog = null))
            service.delete(i.monitored.uid).await()
            emit(state.copy(status = Feedback.Success("Successfully delete ${i.monitored.name}, Loading remaining businesses . . ."), dialog = null))
            val phase = state.copy(
                status = Feedback.None,
                table = businessTable(service.all().await()),
                dialog = null
            )
            emit(phase)
        }.catchAndCollectToUI(state)
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

    private fun businessTable(data: List<MonitoredBusinessSummary>) = tableOf(data) {
        primaryAction("Add Business") { post(ShowCreateBusinessForm) }
        singleAction("Intervene") { post(ShowInterveneForm(it.data)) }
        singleAction("Capture Investment") { post(ShowCaptureInvestmentForm(it.data)) }
        singleAction("Delete") { post(ShowDeleteSingleConfirmationDialog(it.data)) }
        multiAction("Delete All") { post(ShowDeleteMultipleConfirmationDialog(it)) }
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
            action("Invite to share reports") { post(ShowInviteToShareReportsForm(it.data)) }
            action("Intervene") { post(ShowInterveneForm(it.data)) }
            action("Capture Investment") { post(ShowCaptureInvestmentForm(it.data)) }
            action("Delete") { post(ShowDeleteSingleConfirmationDialog(it.data)) }
        }
    }
}