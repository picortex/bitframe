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
import pimonitor.client.PiMonitorApi
import pimonitor.client.businesses.BusinessesIntent.*
import pimonitor.client.businesses.dialogs.*
import pimonitor.core.businesses.models.MonitoredBusinessSummary
import pimonitor.core.businesses.params.InviteMessageParams
import pimonitor.core.businesses.params.copy
import presenters.containers.toString
import presenters.feedbacks.Feedback.*
import presenters.table.builders.tableOf
import viewmodel.ViewModel
import pimonitor.client.businesses.BusinessesIntent as Intent
import pimonitor.client.businesses.BusinessesState as State

class BusinessesViewModel(
    private val config: UIScopeConfig<PiMonitorApi>
) : ViewModel<Intent, State>(State(), config.viewModel) {

    private val api get() = config.service

    override fun CoroutineScope.execute(i: Intent): Any = when (i) {
        LoadBusinesses -> loadBusiness()

        ShowCreateBusinessForm -> showCreateBusinessForm()
        is SendCreateBusinessForm -> sendCreateBusinessForm(i)

        is ShowInviteToShareReportsForm -> showInviteToShareReportsForm(i)
        is SendInviteToShareReportsForm -> sendInviteToShareReportsForm(i)

        is ShowInterveneForm -> showInterveneForm(i)
        is ShowCaptureInvestmentForm -> showCaptureInvestmentForm(i)
        is ShowDeleteSingleConfirmationDialog -> showDeleteSingleConfirmationDialog(i)
        is ShowDeleteMultipleConfirmationDialog -> showDeleteMultipleConfirmationDialog(i)

        ExitDialog -> exitDialog()
        is Delete -> delete(i)
        is DeleteAll -> deleteAll(i)
    }

    private fun showDeleteMultipleConfirmationDialog(i: ShowDeleteMultipleConfirmationDialog) {
        ui.value = ui.value.copy(status = None, focus = null, dialog = DeleteManyDialog(i.data) {
            onCancel { post(ExitDialog) }
            onConfirm { post(DeleteAll(i.data.map { it.data }.toTypedArray())) }
        })
    }

    private fun showDeleteSingleConfirmationDialog(i: ShowDeleteSingleConfirmationDialog) {
        ui.value = ui.value.copy(status = None, focus = i.monitored, dialog = DeleteSingleDialog(i.monitored) {
            onCancel { post(ExitDialog) }
            onConfirm { post(Delete(i.monitored)) }
        })
    }

    private fun showCaptureInvestmentForm(i: ShowCaptureInvestmentForm) {
        ui.value = ui.value.copy(status = None, focus = i.monitored, dialog = CaptureInvestmentDialog(i.monitored) {
            onCancel { post(ExitDialog) }
            onSubmit { params: Any -> TODO() }
        })
    }

    private fun showInterveneForm(i: ShowInterveneForm) {
        ui.value = ui.value.copy(status = None, focus = i.monitored, dialog = InterveneDialog(i.monitored) {
            onCancel { post(ExitDialog) }
            onSubmit { params: Any -> TODO() }
        })
    }

    private fun showCreateBusinessForm() {
        ui.value = ui.value.copy(status = None, focus = null, dialog = CreateBusinessDialog {
            onCancel { post(ExitDialog) }
            onSubmit { params -> post(SendCreateBusinessForm(params)) }
        })
    }

    private fun CoroutineScope.showInviteToShareReportsForm(i: ShowInviteToShareReportsForm) = launch {
        val state = ui.value
        flow {
            emit(state.copy(status = Loading("Preparing invite form, please wait . . ."), focus = i.monitored, dialog = null))
            val inviteInfo = api.invites.defaultInviteMessage(InviteMessageParams(i.monitored.uid)).await()
            val dialog = InviteToShareReportsDialog(
                businessName = i.monitored.name,
                contactEmail = i.monitored.contacts.filterIsInstance<UserEmail>().firstOrNull()?.value ?: error("There are no registered contact's with email in ${i.monitored.name}"),
                message = inviteInfo.inviteMessage
            ) {
                onCancel { post(ExitDialog) }
                onSubmit { params -> post(SendInviteToShareReportsForm(params)) }
            }
            emit(state.copy(status = None, focus = i.monitored, dialog = dialog))
        }.catchAndCollectToUI(state)
    }

    private fun CoroutineScope.sendInviteToShareReportsForm(i: SendInviteToShareReportsForm) = launch {
        val state = ui.value
        flow {
            val focused = state.focus ?: error("There is no business that is currently focused on")
            emit(state.copy(status = Loading("Sending invite to ${focused.name}, Please wait . . ."), dialog = null))
            api.invites.send(i.params.copy(focused.uid)).await()
            emit(state.copy(status = Success("Invite Sent. Loading your businesses, please wait . . ."), dialog = null))
            emit(state.copy(status = None, table = businessTable(api.businesses.all().await()), dialog = null))
        }.catchAndCollectToUI(ui.value)
    }

    private fun CoroutineScope.sendCreateBusinessForm(i: SendCreateBusinessForm) = launch {
        val state = ui.value
        flow {
            emit(state.copy(status = Loading("Adding ${i.params.businessName}, please wait . . ."), dialog = null))
            val result = api.businesses.create(i.params).await()
            if (result.params.sendInvite) {
                emit(state.copy(status = Success("${i.params.businessName} has successfully been added. Preparing invite form, please wait . . ."), dialog = null))
                val inviteInfo = api.invites.defaultInviteMessage(InviteMessageParams(result.business.uid)).await()
                val dialog = InviteToShareReportsDialog(
                    businessName = result.params.businessName, contactEmail = result.params.contactEmail, message = inviteInfo.inviteMessage
                ) {
                    onCancel { post(LoadBusinesses) }
                    onSubmit { params -> post(SendInviteToShareReportsForm(params)) }
                }
                emit(state.copy(status = None, dialog = dialog, focus = result.summary))
            } else {
                emit(state.copy(status = Success("${i.params.businessName} has successfully been added"), dialog = null))
                delay(config.viewModel.transitionTime)
                emit(state.copy(status = Loading("Loading your businesses, please wait . . ."), dialog = null))
                emit(state.copy(status = None, table = businessTable(api.businesses.all().await()), dialog = null))
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
            api.businesses.delete(*i.data.map { it.uid }.toTypedArray()).await()
            emit(state.copy(status = Success("${i.data.size} businesses deleted successfully, Loading remaining businesses . . ."), dialog = null))
            val phase = state.copy(
                status = None, table = businessTable(api.businesses.all().await()), dialog = null
            )
            emit(phase)
        }.catchAndCollectToUI(state)
    }

    private fun CoroutineScope.delete(i: Delete) = launch {
        val state = ui.value
        flow {
            emit(state.copy(status = Loading("Deleting ${i.monitored.name}"), dialog = null))
            api.businesses.delete(i.monitored.uid).await()
            emit(state.copy(status = Success("Successfully delete ${i.monitored.name}, Loading remaining businesses . . ."), dialog = null))
            emit(state.copy(status = None, table = businessTable(api.businesses.all().await()), dialog = null))
        }.catchAndCollectToUI(state)
    }

    private fun CoroutineScope.captureInvestment(i: ShowCaptureInvestmentForm) = launch {
        val state = ui.value
        flow {
            emit(state.copy(status = Loading("Capturing investment"), focus = null, dialog = null))
            error("Implement capture investment for ${i.monitored}")
        }.catchAndCollectToUI(state)
    }

    private fun exitDialog() {
        val state = ui.value
        if (state.dialog != null) {
            ui.value = state.copy(status = None, dialog = null, focus = null)
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
            emit(state.copy(status = None, table = businessTable(api.businesses.all().await()), dialog = null))
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
        column("Operations") { it.data.operationalBoard }
        column("Accounting") { it.data.financialBoard }
        column("Revenue") { it.data.revenue.toString() }
        column("Expenses") { it.data.expenses.toString() }
        column("GP") { it.data.grossProfit.toString() }
        column("Velocity") { it.data.velocity.toString() }
        column("NCF") { it.data.netCashFlow.toString() }
        column("V/day") { it.data.velocity.toString() }
        actionsColumn("Actions") {
            action("Invite to share reports") { post(ShowInviteToShareReportsForm(it.data)) }
            action("Intervene") { post(ShowInterveneForm(it.data)) }
            action("Capture Investment") { post(ShowCaptureInvestmentForm(it.data)) }
            action("Delete") { post(ShowDeleteSingleConfirmationDialog(it.data)) }
        }
    }
}