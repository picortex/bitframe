package pimonitor.client.businesses

import bitframe.client.UIScopeConfig
import bitframe.core.UserEmail
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import later.await
import pimonitor.client.businesses.BusinessesDialogContent.captureInvestmentDialog
import pimonitor.client.businesses.BusinessesDialogContent.createBusinessDialog
import pimonitor.client.businesses.BusinessesDialogContent.deleteManyDialog
import pimonitor.client.businesses.BusinessesDialogContent.deleteSingleDialog
import pimonitor.client.businesses.BusinessesDialogContent.interveneDialog
import pimonitor.client.businesses.BusinessesDialogContent.inviteToShareReportsDialog
import pimonitor.client.businesses.BusinessesIntent.*
import pimonitor.core.businesses.DASHBOARD_OPERATIONAL
import pimonitor.core.businesses.models.MonitoredBusinessSummary
import presenters.feedbacks.Feedback.*
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

        ShowCreateBusinessForm -> showCreateBusinessForm()
        is SubmitCreateBusinessForm -> submitCreateBusinessForm(i)

        is ShowInviteToShareReportsForm -> showInviteToShareReportsForm(i)
        is SubmitInviteToShareReportsForm -> submitInviteToShareReportsForm(i)

        is ShowInterveneForm -> showInterveneForm(i)
        is ShowCaptureInvestmentForm -> showCaptureInvestmentForm(i)
        is ShowDeleteSingleConfirmationDialog -> showDeleteSingleConfirmationDialog(i)
        is ShowDeleteMultipleConfirmationDialog -> showDeleteMultipleConfirmationDialog(i)

        ExitDialog -> exitDialog()
        is Delete -> delete(i)
        is DeleteAll -> deleteAll(i)
    }

    private fun showDeleteMultipleConfirmationDialog(i: ShowDeleteMultipleConfirmationDialog) {
        ui.value = ui.value.copy(
            status = None,
            dialog = deleteManyDialog(i.data) {
                onCancel { post(ExitDialog) }
                onConfirm { post(DeleteAll(i.data.map { it.data }.toTypedArray())) }
            }
        )
    }

    private fun showDeleteSingleConfirmationDialog(i: ShowDeleteSingleConfirmationDialog) {
        ui.value = ui.value.copy(
            status = None,
            dialog = deleteSingleDialog(i.monitored) {
                onCancel { post(ExitDialog) }
                onConfirm { post(Delete(i.monitored)) }
            }
        )
    }

    private fun showCaptureInvestmentForm(i: ShowCaptureInvestmentForm) {
        ui.value = ui.value.copy(
            status = None,
            dialog = captureInvestmentDialog(i.monitored) {
                onCancel { post(ExitDialog) }
                onSubmit { params: Unit -> TODO() }
            }
        )
    }

    private fun showInterveneForm(i: ShowInterveneForm) {
        ui.value = ui.value.copy(
            status = None,
            dialog = interveneDialog(i.monitored) {
                onCancel { post(ExitDialog) }
                onSubmit { params: Unit -> TODO() }
            }
        )
    }

    private fun showCreateBusinessForm() {
        ui.value = ui.value.copy(
            status = None,
            dialog = createBusinessDialog {
                onCancel { post(ExitDialog) }
                onSubmit { params -> post(SubmitCreateBusinessForm(params)) }
            }
        )
    }

    private fun showInviteToShareReportsForm(i: ShowInviteToShareReportsForm) {
        val dialog = inviteToShareReportsDialog(
            businessName = i.monitored.name,
            contactEmail = i.monitored.contacts.filterIsInstance<UserEmail>().firstOrNull()?.value ?: error("There are no registered contact's with email in ${i.monitored.name}")
        ) {
            onCancel { post(ExitDialog) }
            onSubmit { params -> post(SubmitInviteToShareReportsForm(params)) }
        }
        ui.value = ui.value.copy(status = None, dialog = dialog)
    }

    private fun CoroutineScope.submitInviteToShareReportsForm(i: SubmitInviteToShareReportsForm) = launch {
        val state = ui.value
        flow {
            emit(state.copy(status = Loading("Sending invite to ${i.params.business.name}, Please wait . . ."), dialog = null))
            service.invite(i.params).await()
            emit(state.copy(status = Success("Invite Sent"), dialog = null))
            delay(config.viewModel.transitionTime)
            emit(state.copy(status = None, dialog = null))
        }.catchAndCollectToUI(ui.value)
    }

    private fun CoroutineScope.submitCreateBusinessForm(i: SubmitCreateBusinessForm) = launch {
        val state = ui.value
        flow {
            emit(state.copy(status = Loading("Adding ${i.params.businessName}, please wait . . ."), dialog = null))
            val result = service.create(i.params).await().params
            emit(state.copy(status = Success("${i.params.businessName} has successfully been added"), dialog = null))
            if (result.sendInvite) {
                val dialog = inviteToShareReportsDialog(
                    businessName = result.businessName,
                    contactEmail = result.contactEmail
                ) {
                    onCancel { post(LoadBusinesses) }
                    onSubmit { params -> post(SubmitInviteToShareReportsForm(params)) }
                }
                emit(state.copy(status = None, dialog = dialog))
            } else {
                delay(config.viewModel.transitionTime)
                emit(state.copy(status = Loading("Loading your businesses, please wait . . ."), dialog = null))
                emit(state.copy(status = None, table = businessTable(service.all().await()), dialog = null))
            }
        }.catchAndCollectToUI(state)
    }

    private fun CoroutineScope.intervene(i: ShowInterveneForm) = launch {
        val state = ui.value
        flow {
            emit(state.copy(status = Loading("Intervening"), dialog = null))
            error("Implement intervene for ${i.monitored.name}")
        }.catchAndCollectToUI(state)
    }

    private fun CoroutineScope.deleteAll(i: DeleteAll) = launch {
        val state = ui.value
        flow {
            emit(state.copy(status = Loading("Deleting ${i.data.size} businesses")))
            service.delete(*i.data.map { it.uid }.toTypedArray()).await()
            emit(state.copy(status = Success("${i.data.size} businesses deleted successfully, Loading remaining businesses . . ."), dialog = null))
            val phase = state.copy(
                status = None,
                table = businessTable(service.all().await()),
                dialog = null
            )
            emit(phase)
        }.catchAndCollectToUI(state)
    }

    private fun CoroutineScope.delete(i: Delete) = launch {
        val state = ui.value
        flow {
            emit(state.copy(status = Loading("Deleting ${i.monitored.name}"), dialog = null))
            service.delete(i.monitored.uid).await()
            emit(state.copy(status = Success("Successfully delete ${i.monitored.name}, Loading remaining businesses . . ."), dialog = null))
            emit(state.copy(status = None, table = businessTable(service.all().await()), dialog = null))
        }.catchAndCollectToUI(state)
    }

    private fun CoroutineScope.captureInvestment(i: ShowCaptureInvestmentForm) = launch {
        val state = ui.value
        flow {
            emit(state.copy(status = Loading("Capturing investment")))
            error("Implement capture investment for ${i.monitored}")
        }.catchAndCollectToUI(state)
    }

    private fun exitDialog() {
        val state = ui.value
        if (state.dialog != null) {
            ui.value = state.copy(status = None, dialog = null)
        }
    }

    private suspend fun Flow<State>.catchAndCollectToUI(state: State) = catch {
        emit(state.copy(status = Failure(it), dialog = null))
        delay(config.viewModel.recoveryTime)
        emit(state.copy(status = None, dialog = null))
    }.collect {
        ui.value = it
    }

    private fun CoroutineScope.loadBusiness() = launch {
        val state = ui.value
        flow {
            emit(state.copy(status = Loading("Loading your businesses, please wait . . ."), dialog = null))
            emit(state.copy(status = None, table = businessTable(service.all().await()), dialog = null))
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
        column("Reporting") { it.data.operationalBoard }
        column("Accounting") { it.data.financialBoard }
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