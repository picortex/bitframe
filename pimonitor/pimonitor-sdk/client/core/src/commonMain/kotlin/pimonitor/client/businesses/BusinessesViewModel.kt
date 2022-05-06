package pimonitor.client.businesses

import bitframe.client.UIScopeConfig
import bitframe.core.UserEmail
import kotlinx.collections.interoperable.toInteroperableList
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import later.await
import pimonitor.client.PiMonitorApi
import pimonitor.client.businesses.BusinessesIntent.*
import pimonitor.client.businesses.dialogs.DeleteManyDialog
import pimonitor.client.businesses.dialogs.DeleteSingleDialog
import pimonitor.client.businesses.fields.InviteToShareReportsForm
import pimonitor.client.businesses.forms.CreateBusinessForm
import pimonitor.client.interventions.forms.CreateInterventionForm
import pimonitor.client.investments.forms.CreateInvestmentForm
import pimonitor.client.utils.live.removeEmphasis
import pimonitor.client.utils.live.update
import pimonitor.core.businesses.MonitoredBusinessColumns
import pimonitor.core.businesses.models.MonitoredBusinessSummary
import pimonitor.core.businesses.params.InviteMessageParams
import pimonitor.core.businesses.params.toValidatedParams
import pimonitor.core.investments.params.toValidatedParams
import presenters.cases.CentralState
import presenters.cases.Emphasis.Companion.Dialog
import presenters.cases.Emphasis.Companion.Failure
import presenters.cases.Emphasis.Companion.Loading
import presenters.cases.Emphasis.Companion.Success
import presenters.cases.Emphasis.None
import presenters.table.builders.tableOf
import viewmodel.ViewModel
import pimonitor.client.businesses.BusinessesIntent as Intent

class BusinessesViewModel(
    private val config: UIScopeConfig<PiMonitorApi>
) : ViewModel<Intent, CentralState<*, MonitoredBusinessSummary>>(INITIAL_LOADING_STATE, config.viewModel) {
    companion object {
        private val INITIAL_LOADING_STATE = CentralState<Any?, MonitoredBusinessSummary>(emphasis = Loading("Loading businesses, please wait. . ."))
    }

    private val api get() = config.service

    override fun CoroutineScope.execute(i: Intent): Any = when (i) {
        LoadBusinesses -> loadBusinesses()

        is ShowCreateBusinessForm -> showCreateBusinessForm(i)
        is SendCreateBusinessForm -> sendCreateBusinessForm(i)

        is ShowInviteToShareReportsForm -> showInviteToShareReportsForm(i)
        is SendInviteToShareReportsForm -> sendInviteToShareReportsForm(i)

        is ShowCreateInterventionForm -> showInterveneForm(i)
        is SendCreateInterventionForm -> sendInterveneForm(i)

        is ShowCreateInvestmentForm -> showCaptureInvestmentForm(i)
        is SendCreateInvestmentForm -> sendCaptureInvestmentForm(i)

        is ShowDeleteSingleConfirmationDialog -> showDeleteSingleConfirmationDialog(i)
        is ShowDeleteMultipleConfirmationDialog -> showDeleteMultipleConfirmationDialog(i)

        is Delete -> delete(i)
        is DeleteAll -> deleteAll(i)
    }

    private fun CoroutineScope.sendInterveneForm(i: SendCreateInterventionForm) = launch {
        val state = ui.value
        flow {
            emit(state.copy(emphasis = Loading("Intervening ${i.monitored.name}, please wait . . .")))
            api.interventions.create(i.params).await()
            emit(state.copy(emphasis = Success("Intervention completed successfully. Loading businesses, please wait . . .")))
            emit(state.copy(table = businessesTable(api.businesses.all().await())))
        }.catch {
            emit(state.copy(emphasis = Failure(it) {
                onGoBack { post(ShowCreateInterventionForm(i.monitored, i.params)) }
                onRetry { post(i) }
            }))
        }.collect {
            ui.value = it
        }
    }

    private fun showDeleteMultipleConfirmationDialog(i: ShowDeleteMultipleConfirmationDialog) {
        val confirm = DeleteManyDialog(i.data) {
            onCancel { ui.removeEmphasis() }
            onConfirm { post(DeleteAll(i.data.map { it.data }.toTypedArray())) }
        }
        ui.update { copy(emphasis = Dialog(confirm)) }
    }

    private fun showDeleteSingleConfirmationDialog(i: ShowDeleteSingleConfirmationDialog) {
        val state = ui.value
        val confirm = DeleteSingleDialog(i.monitored) {
            onCancel { ui.value = state.copy(emphasis = None) }
            onConfirm { post(Delete(i.monitored)) }
        }
        ui.update { copy(emphasis = Dialog(confirm)) }
    }

    private fun CoroutineScope.sendCaptureInvestmentForm(i: SendCreateInvestmentForm) = launch {
        val state = ui.value
        flow {
            emit(state.copy(emphasis = Loading("Capturing investment for ${i.monitored.name}, please wait. . .")))
            val params = i.params.toValidatedParams()
            api.investments.create(params).await()
            emit(state.copy(emphasis = Success("Investment captured successfuly. Loading remaining business, please wait . . .")))
            emit(state.copy(table = businessesTable(api.businesses.all().await())))
        }.catch {
            emit(state.copy(emphasis = Failure(it) {
                onGoBack { post(ShowCreateInvestmentForm(i.monitored, i.params)) }
                onRetry { post(i) }
            }))
        }.collect {
            ui.value = it
        }
    }

    private fun showCaptureInvestmentForm(i: ShowCreateInvestmentForm) {
        val state = ui.value
        val form = CreateInvestmentForm(
            businesses = state.table.rows.map { it.data }.toInteroperableList(),
            business = i.monitored,
            params = i.params
        ) {
            onCancel { ui.value = state.copy(emphasis = None) }
            onSubmit { params -> post(SendCreateInvestmentForm(i.monitored, params)) }
        }
        ui.value = ui.value.copy(emphasis = Dialog(form))
    }

    private fun showInterveneForm(i: ShowCreateInterventionForm) {
        val state = ui.value
        val form = CreateInterventionForm(
            businesses = state.table.rows.map { it.data },
            business = i.monitored,
            params = i.params
        ) {
            onCancel { ui.value = state.copy(emphasis = None) }
            onSubmit("Create Intervention") { post(SendCreateInterventionForm(i.monitored, it)) }
        }
        ui.value = ui.value.copy(emphasis = Dialog(form))
    }

    private fun showCreateBusinessForm(i: ShowCreateBusinessForm) {
        val state = ui.value
        val form = CreateBusinessForm(i.params) {
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
                val (inviteInfo, businesses) = coroutineScope {
                    val i = api.invites.defaultInviteMessage(InviteMessageParams(result.business.uid))
                    val b = api.businesses.all()
                    i.await() to b.await()
                }
                val form = InviteToShareReportsForm(
                    businessName = result.params.businessName, contactEmail = result.params.contactEmail, message = inviteInfo.inviteMessage
                ) {
                    onCancel { ui.value = ui.value.copy(emphasis = None) }
                    onSubmit("Send Invite") { post(SendInviteToShareReportsForm(result.summary, it)) }
                }
                emit(state.copy(emphasis = Dialog(form), table = businessesTable(businesses)))
            } else {
                emit(state.copy(emphasis = Success("${i.params.businessName} has successfully been added. Loading all your businesses, please wait . . .")))
                emit(state.copy(table = businessesTable(api.businesses.all().await())))
            }
        }.catch {
            emit(state.copy(emphasis = Failure(it) {
                onGoBack { post(ShowCreateBusinessForm(i.params)) }
            }))
        }.collect {
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
        }.collect {
            ui.value = it
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
        }.collect {
            ui.value = it
        }
    }

    private fun CoroutineScope.loadBusinesses() = launch {
        val state = ui.value
        flow {
            emit(INITIAL_LOADING_STATE)
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
        singleAction("Intervene") { post(ShowCreateInterventionForm(it.data, null)) }
        singleAction("Capture Investment") { post(ShowCreateInvestmentForm(it.data, null)) }
        singleAction("Delete") { post(ShowDeleteSingleConfirmationDialog(it.data)) }
        multiAction("Delete All") { post(ShowDeleteMultipleConfirmationDialog(it)) }
        selectable()
        MonitoredBusinessColumns()
        actions("Actions") {
            action("Invite to share reports") { post(ShowInviteToShareReportsForm(it.data, null)) }
            action("Intervene") { post(ShowCreateInterventionForm(it.data, null)) }
            action("Capture Investment") { post(ShowCreateInvestmentForm(it.data, null)) }
            action("Delete") { post(ShowDeleteSingleConfirmationDialog(it.data)) }
        }
    }
}