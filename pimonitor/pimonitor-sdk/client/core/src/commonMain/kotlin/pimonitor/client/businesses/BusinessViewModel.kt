package pimonitor.client.businesses

import bitframe.client.UIScopeConfig
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import pimonitor.client.PiMonitorApi
import pimonitor.client.businesses.BusinessesIntent.*
import pimonitor.core.businesses.models.MonitoredBusinessSummary
import presenters.collections.tableOf
import viewmodel.ViewModel
import pimonitor.client.businesses.BusinessesIntent as Intent
import pimonitor.client.businesses.BusinessesState as State

class BusinessViewModel(
    private val config: UIScopeConfig<PiMonitorApi>
) : ViewModel<Intent, State>(State.Loading("Loading business"), config.viewModel) {

//    private val service: BusinessesService = config.service.businesses

    override fun CoroutineScope.execute(i: Intent): Any = when (i) {
        LoadBusinesses -> loadBusiness()
        ShowCreateBusinessForm -> showBusinessForm()
        is ShowInviteToShareReportsForm -> inviteToShareReports(i)
        ExitDialog -> exitDialog()
    }

    private fun exitDialog() {
        val state = ui.value as? State.Businesses ?: return
        if (state.dialog !is BusinessesDialog.None) {
            ui.value = state.copy(dialog = BusinessesDialog.None())
        }
    }

    private fun CoroutineScope.inviteToShareReports(i: ShowInviteToShareReportsForm) = launch {
        flow<State> {
            val state = ui.value as? State.Businesses ?: error("Can't show invite business form while business have not fully loaded")
            emit(state.copy(dialog = BusinessesDialog.InviteToShareReports(monitored = i.monitored)))
        }.catch {
            emit(State.Failure(it))
        }.collect {
            ui.value = it
        }
    }

    private fun CoroutineScope.showBusinessForm() = launch {
        flow<State> {
            val state = ui.value as? State.Businesses ?: error("Can't show create business form while not fully loaded")
            emit(state.copy(dialog = BusinessesDialog.CreateBusiness()))
        }.catch {
            emit(State.Failure(it))
        }.collect {
            ui.value = it
        }
    }

    private fun CoroutineScope.loadBusiness() = launch {
        flow<State> {
            emit(State.Loading("Loading business, please wait . . ."))
//            val businesses = service.all().await()
//            val state = State.Businesses(
//                table = businessTable(businesses),
//                dialog = BusinessesDialog.None()
//            )
//            emit(state)
        }.catch {
            emit(State.Failure(it))
        }.collect {
            ui.value = it
        }
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