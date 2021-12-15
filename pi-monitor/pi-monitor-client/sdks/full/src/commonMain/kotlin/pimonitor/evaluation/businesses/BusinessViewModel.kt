package pimonitor.evaluation.businesses

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import later.await
import pimonitor.evaluation.businesses.BusinessesIntent.*
import pimonitor.monitored.MonitoredBusiness
import presenters.collections.tableOf
import viewmodel.ViewModel
import pimonitor.evaluation.businesses.BusinessesIntent as Intent
import pimonitor.evaluation.businesses.BusinessesState as State

class BusinessViewModel(
    val service: BusinessesService
) : ViewModel<Intent, State>(State.Loading("Loading business")) {

    override fun CoroutineScope.execute(i: Intent): Any = when (i) {
        LoadBusinesses -> loadBusiness()
        ShowBusinessForm -> showBusinessForm()
        is InviteToShareReports -> inviteToShareReports(i)
    }

    private fun CoroutineScope.inviteToShareReports(i: InviteToShareReports) = launch {
        flow<State> {
            val state = ui.value as? State.Businesses ?: error("Can't show invite business form while business have not fully loaded")
            emit(state.copy(dialog = BusinessesDialog.InviteToShareReports))
        }.catch {
            emit(State.Failure(it))
        }.collect {
            ui.value = it
        }
    }

    private fun CoroutineScope.showBusinessForm() = launch {
        flow<State> {
            val state = ui.value as? State.Businesses ?: error("Can't show create business form while not fully loaded")
            emit(state.copy(dialog = BusinessesDialog.CreateBusiness))
        }.catch {
            emit(State.Failure(it))
        }.collect {
            ui.value = it
        }
    }

    private fun CoroutineScope.loadBusiness() = launch {
        flow {
            emit(State.Loading("Loading business, please wait . . ."))
            val businesses = service.all().await()
            val state = State.Businesses(
                table = businessTable(businesses),
                dialog = BusinessesDialog.None()
            )
            emit(state)
        }.catch {
            emit(State.Failure(it))
        }.collect {
            ui.value = it
        }
    }

    private fun businessTable(date: List<MonitoredBusiness>) = tableOf(date) {
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
            action("Invite to share reports") { post(InviteToShareReports(it.data)) }
//            action("Delete") { tree.log("Deleting: ${it.data.name}") }
//            action("View") { tree.log("Viewing: ${it.data.name}") }
        }
    }
}