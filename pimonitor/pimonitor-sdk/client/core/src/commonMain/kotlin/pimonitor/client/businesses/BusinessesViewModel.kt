package pimonitor.client.businesses

import bitframe.client.UIScopeConfig
import bitframe.core.UserEmail
import kotlinx.collections.interoperable.toInteroperableList
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import later.await
import pimonitor.client.PiMonitorApi
import pimonitor.client.business.interventions.params.toCreateInterventionParams
import pimonitor.client.businesses.BusinessesIntent.*
import pimonitor.client.businesses.dialogs.*
import pimonitor.client.investments.forms.CreateInvestmentForm
import pimonitor.core.businesses.models.MonitoredBusinessSummary
import pimonitor.core.businesses.params.InviteMessageParams
import pimonitor.core.businesses.params.toValidatedParams
import pimonitor.core.investments.params.toValidatedParams
import presenters.cases.Feedback.*
import presenters.cases.CentralState
import presenters.cases.Emphasis
import presenters.cases.Emphasis.Companion.Dialog
import presenters.cases.Emphasis.Companion.Failure
import presenters.cases.Emphasis.Companion.Loading
import presenters.cases.Emphasis.Companion.Success
import presenters.cases.Emphasis.None
import presenters.changes.toString
import presenters.containers.toString
import presenters.table.builders.tableOf
import viewmodel.ViewModel
import pimonitor.client.businesses.BusinessesIntent as Intent

class BusinessesViewModel(
    private val config: UIScopeConfig<PiMonitorApi>
) : ViewModel<Intent, CentralState<MonitoredBusinessSummary>>(CentralState(emphasis = Loading("Loading businesses, please wait. . .")), config.viewModel) {

    private val api get() = config.service

    override fun CoroutineScope.execute(i: Intent): Any = when (i) {
        LoadBusinesses -> loadBusinesses()

        is ShowCreateBusinessForm -> showCreateBusinessForm()
        is SendCreateBusinessForm -> sendCreateBusinessForm(i)

        is ShowInviteToShareReportsForm -> showInviteToShareReportsForm(i)
        is SendInviteToShareReportsForm -> sendInviteToShareReportsForm(i)

        is ShowInterveneForm -> showInterveneForm(i)
        is SendInterveneForm -> sendInterveneForm(i)

        is ShowCaptureInvestmentForm -> showCaptureInvestmentForm(i)
        is SendCaptureInvestmentForm -> sendCaptureInvestmentForm(i)

        is ShowDeleteSingleConfirmationDialog -> showDeleteSingleConfirmationDialog(i)
        is ShowDeleteMultipleConfirmationDialog -> showDeleteMultipleConfirmationDialog(i)

        is Delete -> delete(i)
        is DeleteAll -> deleteAll(i)
    }

    private fun CoroutineScope.sendInterveneForm(i: SendInterveneForm) = launch {
        val state = ui.value
        flow {
            emit(state.copy(emphasis = Loading("Intervening ${i.monitored.name}, please wait . . .")))
            val params = i.params.toCreateInterventionParams(businessId = i.monitored.uid)
            api.businessInterventions.create(params).await()
            emit(state.copy(emphasis = Success("Intervention completed successfully. Loading businesses, please wait . . .")))
            emit(state.copy(table = businessesTable(api.businesses.all().await())))
        }.catch {
            emit(state.copy(emphasis = Failure(it) {
                onGoBack { post(ShowInterveneForm(i.monitored, i.params)) }
                onRetry { post(i) }
            }))
        }.collect {
            ui.value = it
        }
    }

    private fun showDeleteMultipleConfirmationDialog(i: ShowDeleteMultipleConfirmationDialog) {
        val state = ui.value
        val confirm = DeleteManyDialog(i.data) {
            onCancel { ui.value = state.copy(emphasis = None) }
            onConfirm { post(DeleteAll(i.data.map { it.data }.toTypedArray())) }
        }
        ui.value = ui.value.copy(emphasis = Dialog(confirm))
    }

    private fun showDeleteSingleConfirmationDialog(i: ShowDeleteSingleConfirmationDialog) {
        val state = ui.value
        val confirm = DeleteSingleDialog(i.monitored) {
            onCancel { ui.value = state.copy(emphasis = None) }
            onConfirm { post(Delete(i.monitored)) }
        }
        ui.value = ui.value.copy(emphasis = Dialog(confirm))
    }

    private fun CoroutineScope.sendCaptureInvestmentForm(i: SendCaptureInvestmentForm) = launch {
        val state = ui.value
        flow {
            emit(state.copy(emphasis = Loading("Capturing investment for ${i.monitored.name}, please wait. . .")))
            val params = i.params.toValidatedParams()
            api.businessInvestments.capture(params).await()
            emit(state.copy(emphasis = Success("Investment captured successfuly. Loading remaining business, please wait . . .")))
            emit(state.copy(table = businessesTable(api.businesses.all().await())))
        }.catch {
            emit(state.copy(emphasis = Failure(it) {
                onGoBack { post(ShowCaptureInvestmentForm(i.monitored, i.params)) }
                onRetry { post(i) }
            }))
        }.collect {
            ui.value = it
        }
    }

    private fun showCaptureInvestmentForm(i: ShowCaptureInvestmentForm) {
        val state = ui.value
        val form = CreateInvestmentForm(
            businesses = state.table.rows.map { it.data }.toInteroperableList(),
            business = i.monitored,
            params = i.params
        ) {
            onCancel { ui.value = state.copy(emphasis = None) }
            onSubmit { params -> post(SendCaptureInvestmentForm(i.monitored, params)) }
        }
        ui.value = ui.value.copy(emphasis = Dialog(form))
    }

    private fun showInterveneForm(i: ShowInterveneForm) {
        val state = ui.value
        val form = InterveneForm(i.monitored) {
            onCancel { ui.value = state.copy(emphasis = None) }
            onSubmit("Create Intervention") { post(SendInterveneForm(i.monitored, it)) }
        }
        ui.value = ui.value.copy(emphasis = Dialog(form))
    }

    private fun showCreateBusinessForm() {
        val state = ui.value
        val form = CreateBusinessForm {
            onCancel { ui.value = state.copy(emphasis = None) }
            onSubmit("Create Business") { params -> post(SendCreateBusinessForm(params)) }
        }
        ui.value = ui.value.copy(emphasis = Dialog(form))
    }

    private fun CoroutineScope.showInviteToShareReportsForm(i: ShowInviteToShareReportsForm) = launch {
        val state = ui.value
        flow {
            emit(state.copy(emphasis = Loading("Preparing invite form, please wait . . .")))
            val inviteInfo = api.invites.defaultInviteMessage(InviteMessageParams(i.monitored.uid)).await()
            val form = InviteToShareReportsForm(
                businessName = i.monitored.name,
                contactEmail = i.monitored.contacts.filterIsInstance<UserEmail>().firstOrNull()?.value ?: error("There are no registered contact's with email in ${i.monitored.name}"),
                message = inviteInfo.inviteMessage
            ) {
                onCancel { ui.value = state.copy(emphasis = None) }
                onSubmit("Send Invite") { params -> post(SendInviteToShareReportsForm(i.monitored, params)) }
            }
            emit(state.copy(emphasis = Dialog(form)))
        }.catch {
            emit(state.copy(emphasis = Failure(it) {
                onGoBack { ui.value = state.copy(emphasis = None) }
                onRetry { post(i) }
            }))
        }.collect {
            ui.value = it
        }
    }

    private fun CoroutineScope.sendInviteToShareReportsForm(i: SendInviteToShareReportsForm) = launch {
        val state = ui.value
        flow {
            emit(state.copy(emphasis = Loading("Sending invite to ${i.monitored.name}, Please wait . . .")))
            api.invites.send(i.params.toValidatedParams(i.monitored.uid)).await()
            emit(state.copy(emphasis = Success("Invite Sent. Loading your businesses, please wait . . .")))
            emit(state.copy(table = businessesTable(api.businesses.all().await())))
        }.catch {
            emit(state.copy(emphasis = Failure(it) {
                onGoBack { post(ShowInviteToShareReportsForm(i.monitored, i.params)) }
                onRetry { post(i) }
            }))
        }.collect {
            ui.value = it
        }
    }

    private fun CoroutineScope.sendCreateBusinessForm(i: SendCreateBusinessForm) = launch {
        val state = ui.value
        flow {
            emit(state.copy(emphasis = Loading("Adding ${i.params.businessName}, please wait . . .")))
            val result = api.businesses.create(i.params).await()
            if (result.params.sendInvite) {
                emit(state.copy(emphasis = Success("${i.params.businessName} has successfully been added. Preparing invite form, please wait . . .")))
                val inviteInfo = api.invites.defaultInviteMessage(InviteMessageParams(result.business.uid)).await()
                val form = InviteToShareReportsForm(
                    businessName = result.params.businessName, contactEmail = result.params.contactEmail, message = inviteInfo.inviteMessage
                ) {
                    onCancel { post(LoadBusinesses) }
                    onSubmit("Send Invite") { post(SendInviteToShareReportsForm(result.summary, it)) }
                }
                emit(state.copy(emphasis = Dialog(form)))
            } else {
                emit(state.copy(emphasis = Success("${i.params.businessName} has successfully been added. Loading all your businesses, please wait . . .")))
                emit(state.copy(table = businessesTable(api.businesses.all().await())))
            }
        }.catch {
            emit(state.copy(emphasis = Failure(it) {
                onGoBack { post(ShowCreateBusinessForm(i.params)) }
            }))
        }.collect{
            ui.value = it
        }
    }

    private fun CoroutineScope.deleteAll(i: DeleteAll) = launch {
        val state = ui.value
        flow {
            emit(state.copy(emphasis = Loading("Deleting ${i.data.size} businesses")))
            api.businesses.delete(*i.data.map { it.uid }.toTypedArray()).await()
            emit(state.copy(emphasis = Success("${i.data.size} businesses deleted successfully. Loading remaining businesses, please wait . . .")))
            emit(state.copy(table = businessesTable(api.businesses.all().await())))
        }.catch {
            emit(state.copy(emphasis = Failure(it) {
                onGoBack { ui.value = state.copy(emphasis = None) }
                onRetry { post(i) }
            }))
        }.collect{
            ui.value
        }
    }

    private fun CoroutineScope.delete(i: Delete) = launch {
        val state = ui.value
        flow {
            emit(state.copy(emphasis = Loading("Deleting ${i.monitored.name}")))
            api.businesses.delete(i.monitored.uid).await()
            emit(state.copy(emphasis = Success("Successfully delete ${i.monitored.name}. Loading remaining businesses, please wait . . .")))
            emit(state.copy(table = businessesTable(api.businesses.all().await())))
        }.catch {
            emit(state.copy(emphasis = Failure(it) {
                onGoBack { ui.value = state.copy(emphasis = None) }
                onRetry { post(i) }
            }))
        }.collect{
            ui.value
        }
    }

    private fun CoroutineScope.loadBusinesses() = launch {
        val state = ui.value
        flow {
            emit(state.copy(emphasis = Loading("Loading your businesses, please wait . . .")))
            emit(state.copy(table = businessesTable(api.businesses.all().await())))
        }.catch {
            emit(state.copy(emphasis = Failure(it) {
                onRetry { post(LoadBusinesses) }
            }))
        }.collect {
            ui.value = it
        }
    }

    private fun businessesTable(data: List<MonitoredBusinessSummary>) = tableOf(data) {
        emptyMessage = "No Businesses Found"
        emptyDetails = "You haven't added any businesses to your space yet"
        emptyAction("Create Business") { post(ShowCreateBusinessForm(null)) }
        primaryAction("Add Business") { post(ShowCreateBusinessForm(null)) }
        primaryAction("Refresh") { post(LoadBusinesses) }
        singleAction("Intervene") { post(ShowInterveneForm(it.data, null)) }
        singleAction("Capture Investment") { post(ShowCaptureInvestmentForm(it.data, null)) }
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
        actions("Actions") {
            action("Invite to share reports") { post(ShowInviteToShareReportsForm(it.data, null)) }
            action("Intervene") { post(ShowInterveneForm(it.data, null)) }
            action("Capture Investment") { post(ShowCaptureInvestmentForm(it.data, null)) }
            action("Delete") { post(ShowDeleteSingleConfirmationDialog(it.data)) }
        }
    }
}