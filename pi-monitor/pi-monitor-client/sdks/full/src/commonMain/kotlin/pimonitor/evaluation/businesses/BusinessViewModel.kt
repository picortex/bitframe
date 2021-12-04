package pimonitor.evaluation.businesses

import bitframe.presenters.collections.tableOf
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import later.await
import logging.logger
import pimonitor.evaluation.businesses.BusinessesIntent.LoadBusinesses
import pimonitor.evaluation.businesses.BusinessesIntent.ShowBusinessForm
import pimonitor.evaluation.businesses.BusinessesService
import pimonitor.monitored.MonitoredBusiness
import viewmodel.ViewModel
import pimonitor.evaluation.businesses.BusinessesIntent as Intent
import pimonitor.evaluation.businesses.BusinessesState as State

class BusinessViewModel(
    val service: BusinessesService
) : ViewModel<Intent, State>(State.Loading("Loading business")) {

    override fun CoroutineScope.execute(i: Intent): Any = when (i) {
        LoadBusinesses -> loadBusiness()
        ShowBusinessForm -> ui.value = State.BusinessForm()
    }

    val tree = logger()

    private fun CoroutineScope.loadBusiness() = launch {
        flow {
            emit(State.Loading("Loading business, please wait . . ."))
            val businesses = service.all().await()
            emit(State.Businesses(businessTable(businesses)))
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
            action("Invite to share reports") {}
            action("Delete") { tree.log("Deleting: ${it.data.name}") }
            action("View") { tree.log("Viewing: ${it.data.name}") }
        }
    }
}