package pimonitor.client.businesses

import bitframe.client.UIScopeConfig
import kotlinx.collections.interoperable.toInteroperableList
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import later.await
import pimonitor.client.businesses.BusinessesIntent.*
import pimonitor.core.businesses.DASHBOARD
import pimonitor.core.businesses.models.MonitoredBusinessSummary
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
            status = Feedback.None,
            dialog = BusinessesDialog.CreateBusiness()
        )
        is ShowInviteToShareReportsForm -> ui.value = ui.value.copy(
            status = Feedback.None,
            dialog = BusinessesDialog.InviteToShareReports(monitored = i.monitored)
        )
        ExitDialog -> exitDialog()
        is CaptureInvestment -> captureInvestment(i)
        is Delete -> delete(i)
        is DeleteAll -> deleteAll(i)
        is Intervene -> intervene(i)
        is UpdateInvestment -> updateInvestment(i)
    }

    private fun CoroutineScope.updateInvestment(i: UpdateInvestment) = launch {
        val state = ui.value
        flow {
            emit(state.copy(status = Feedback.Loading("Capturing investment")))
            error("Implement update investment for ${i.monitored}")
        }.catchAndCollectToUI(state)
    }

    private fun CoroutineScope.intervene(i: Intervene) = launch {
        val state = ui.value
        flow {
            emit(state.copy(status = Feedback.Loading("Intervening")))
            error("Implement intervene for ${i.monitored.name}")
        }.catchAndCollectToUI(state)
    }

    private fun CoroutineScope.deleteAll(i: DeleteAll) = launch {
        val state = ui.value
        flow {
            emit(state.copy(status = Feedback.Loading("Deleting All")))
            error("Implement delete all for ${i.data.joinToString(",") { it.name }}")
        }.catchAndCollectToUI(state)
    }

    private fun CoroutineScope.delete(i: Delete) = launch {
        val state = ui.value
        flow {
            emit(state.copy(status = Feedback.Loading("Deleting")))
            error("Implement delete for ${i.monitored}")
        }.catchAndCollectToUI(state)
    }

    private fun CoroutineScope.captureInvestment(i: CaptureInvestment) = launch {
        val state = ui.value
        flow {
            emit(state.copy(status = Feedback.Loading("Capturing investment")))
            error("Implement capture investment for ${i.monitored}")
        }.catchAndCollectToUI(state)
    }

    private fun exitDialog() {
        val state = ui.value
        if (state.dialog !is BusinessesDialog.None) {
            ui.value = state.copy(dialog = BusinessesDialog.None)
        }
    }

    private suspend fun Flow<State>.catchAndCollectToUI(state: State) = catch {
        emit(state.copy(status = Feedback.Failure(it), dialog = BusinessesDialog.None))
        delay(config.viewModel.recoveryTime)
        emit(state)
    }.collect {
        ui.value = it
    }

    private fun CoroutineScope.loadBusiness() = launch {
        val state = ui.value
        flow {
            emit(
                state.copy(
                    status = Feedback.Loading("Loading your businesses, please wait . . ."),
                    dialog = BusinessesDialog.None
                )
            )
            emit(
                state.copy(
                    status = Feedback.None,
                    table = businessTable(service.all().await()),
                    dialog = BusinessesDialog.None
                )
            )
        }.catchAndCollectToUI(state)
    }

    internal fun businessTable(date: List<MonitoredBusinessSummary>) = tableOf(date) {
        primaryAction("Add Business") {
            post(ShowCreateBusinessForm)
        }
        singleAction("Intervene") {
            post(Intervene(it.data))
        }
        singleAction("Capture Investment") {
            post(CaptureInvestment(it.data))
        }
        singleAction("Update Investment") {
            post(UpdateInvestment(it.data))
        }
        singleAction("Delete") {
            post(Delete(it.data))
        }
        multiAction("Delete All") { selected ->
            post(DeleteAll(selected.map { it.data }.toInteroperableList()))
        }
        selectable()
        column("Name") { it.data.name }
        column("Reporting") {
            when (val data = it.data) {
                is MonitoredBusinessSummary.ConnectedDashboard -> data.dashboard
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
            action("Intervene") {
                post(Intervene(it.data))
            }
            action("Capture Investment") {
                post(CaptureInvestment(it.data))
            }
            action("Update Investment") {
                post(UpdateInvestment(it.data))
            }
            action("Delete") { post(Delete(it.data)) }
        }
    }
}