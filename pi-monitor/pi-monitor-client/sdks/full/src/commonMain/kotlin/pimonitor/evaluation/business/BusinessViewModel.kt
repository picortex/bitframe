package pimonitor.evaluation.business

import bitframe.presenters.collections.tableOf
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import later.await
import logging.logger
import pimonitor.evaluation.business.BusinessesIntent.LoadBusinesses
import pimonitor.evaluation.business.BusinessesIntent.ShowBusinessForm
import pimonitor.evaluation.businesses.BusinessesService
import pimonitor.monitored.MonitoredBusiness
import viewmodel.ViewModel
import pimonitor.evaluation.business.BusinessesIntent as Intent
import pimonitor.evaluation.business.BusinessesState as State

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
        column("Contact Name") { it.data.contacts.first().name }
        column("Contact Email") { it.data.contacts.first().email.value }
        actions("Actions") {
            action("Delete") { tree.log("Deleting: ${it.data.name}") }
            action("View") { tree.log("Viewing: ${it.data.name}") }
        }
    }
}