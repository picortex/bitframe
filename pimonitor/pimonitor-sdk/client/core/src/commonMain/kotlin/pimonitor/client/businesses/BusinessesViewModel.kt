package pimonitor.client.businesses

import bitframe.client.UIScopeConfig
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import later.await
import pimonitor.client.PiMonitorApi
import pimonitor.client.businesses.BusinessesIntent.*
import pimonitor.core.businesses.models.MonitoredBusinessSummary
import presenters.collections.tableOf
import presenters.feedbacks.Feedback
import viewmodel.ViewModel
import pimonitor.client.businesses.BusinessesIntent as Intent
import pimonitor.client.businesses.BusinessesState as State

class BusinessesViewModel(
    private val config: UIScopeConfig<BusinessesService>
) : ViewModel<Intent, State>(State(), config.viewModel) {

    private val service: BusinessesService = config.service

    override fun CoroutineScope.execute(i: Intent): Any = when (i) {
        LoadBusinesses -> loadBusiness()
        ShowCreateBusinessForm -> showBusinessForm()
        is ShowInviteToShareReportsForm -> inviteToShareReports(i)
        ExitDialog -> exitDialog()
    }

    private fun exitDialog() {
        val state = ui.value
        if (state.dialog !is BusinessesDialog.None) {
            ui.value = state.copy(dialog = BusinessesDialog.None)
        }
    }

    private suspend fun Flow<State>.catchAndCollectToUI(state: State) = catch {
        emit(state.copy(status = Feedback.Failure(it)))
        delay(config.viewModel.recoveryTime)
        emit(state)
    }.collect {
        ui.value = it
    }

    private fun CoroutineScope.inviteToShareReports(i: ShowInviteToShareReportsForm) = launch {
        val state = ui.value
        flow {
            emit(state.copy(dialog = BusinessesDialog.InviteToShareReports(monitored = i.monitored)))
        }.catchAndCollectToUI(state)
    }

    private fun CoroutineScope.showBusinessForm() = launch {
        val state = ui.value
        flow {
            emit(state.copy(dialog = BusinessesDialog.CreateBusiness()))
        }.catchAndCollectToUI(state)
    }

    private fun CoroutineScope.loadBusiness() = launch {
        val state = ui.value
        flow {
            emit(state.copy(status = Feedback.Loading("Loading your businesses, please wait . . .")))
            emit(
                state.copy(
                    table = businessTable(service.all().await()),
                    dialog = BusinessesDialog.None
                )
            )
        }.catchAndCollectToUI(state)
    }

    private fun businessTable(date: List<MonitoredBusinessSummary>) = tableOf(date) {
        selectable()
        column("Name") { it.data.name }
        column("Reporting") { "" }
        column("Revenue") { "" }
        column("Expenses") { "" }
        column("GP") { "" }
        column("Velocity") { "" }
        column("NCF") { "" }
        column("V/day") { "" }
        actions("Actions") {
            action("Invite to share reports") { post(ShowInviteToShareReportsForm(it.data)) }
//            action("Delete") { tree.log("Deleting: ${it.data.name}") }
//            action("View") { tree.log("Viewing: ${it.data.name}") }
        }
    }
}