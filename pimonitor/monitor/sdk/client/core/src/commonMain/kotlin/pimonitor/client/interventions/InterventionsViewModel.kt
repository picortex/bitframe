package pimonitor.client.interventions

import bitframe.client.UIScopeConfig
import bitframe.core.Identified
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import later.await
import pimonitor.client.MonitorApi
import pimonitor.client.interventions.InterventionsIntent.*
import pimonitor.client.interventions.forms.CreateGoalForm
import pimonitor.client.interventions.forms.CreateInterventionForm
import pimonitor.client.interventions.forms.UpdateInterventionForm
import pimonitor.client.utils.disbursables.DisbursablesIntent
import pimonitor.client.utils.disbursables.DisbursablesIntent.*
import pimonitor.client.utils.disbursables.DisbursablesViewModel
import pimonitor.client.utils.live.removeEmphasis
import pimonitor.client.utils.live.update
import pimonitor.core.interventions.InterventionSummary
import pimonitor.core.interventions.InterventionsColumns
import pimonitor.core.utils.disbursables.filters.DisbursableFilter
import presenters.cases.*
import presenters.table.builders.tableOf

class InterventionsViewModel(
    val config: UIScopeConfig<MonitorApi>
) : DisbursablesViewModel<InterventionSummary>(config, config.service.interventions) {
    override fun CoroutineScope.execute(i: DisbursablesIntent): Any = when (i) {
        is ShowCreateInterventionForm -> showCreateInterventionForm(i)
        is SendCreateInterventionForm -> sendCreateInterventionForm(i)
        is ShowUpdateInterventionForm -> showUpdateInterventionForm(i)
        is SendUpdateInterventionForm -> sendUpdateInterventionForm(i)
        is ShowCreateGoalForm -> showCreateGoalForm(i)
        is SendCreateGoalForm -> sendCreateGoalForm(i)
        is ShowUpdateGoalForm -> showUpdateGoalForm(i)
        is SendUpdateGoalForm -> sendUpdateGoalForm(i)
        else -> perform(i)
    }

    private fun sendUpdateGoalForm(i: SendUpdateGoalForm) {
        val state = ui.value
        ui.update {
            failure(NotImplementedError("Goals feature is not fully implemented. Waiting for webhooks")) {
                onGoBack { state }
            }
        }
    }

    private fun showUpdateGoalForm(i: ShowUpdateGoalForm) {
        val state = ui.value
        ui.update {
            failure(NotImplementedError("Goals feature is not fully implemented. Waiting for webhooks")) {
                onGoBack { state }
            }
        }
    }

    private fun CoroutineScope.showUpdateInterventionForm(i: ShowUpdateInterventionForm) = launch {
        val state = ui.value
        flow {
            emit(state.loading("Preparing interventions form, please wait . . ."))
            val businesses = api.businesses.all().await()
            val business = businesses.firstOrNull { it.uid == state.context?.uid }
            val form = UpdateInterventionForm(
                businesses = businesses,
                business = business,
                params = i.params,
                intervention = i.intervention,
            ) {
                onCancel { ui.removeEmphasis() }
                onSubmit { post(SendCreateInterventionForm(it)) }
            }
            emit(state.dialog(form))
        }.catch {
            emit(state.failure(it) {
                onGoBack { post(ShowCreateInterventionForm(null, i.params)) }
                onRetry { post(i) }
            })
        }.collect {
            ui.update { it }
        }
    }

    private fun CoroutineScope.sendUpdateInterventionForm(i: SendUpdateInterventionForm) = launch {
        val state = ui.value
        flow {
            emit(state.loading("Updating investment, please wait. . .!"))
            val params = Identified(i.intervention.uid, i.params)
            val intervention = api.interventions.update(params).await()
            emit(state.success("Updated ${intervention.name} investment"))
            val interventions = api.interventions.all(DisbursableFilter(state.context?.uid)).await()
            emit(state.table(table = disbursablesTable(interventions)))
        }.catch {
            emit(state.failure(it) {
                onGoBack { post(ShowUpdateInterventionForm(i.intervention, i.params)) }
                onRetry { post(i) }
            })
        }.collect {
            ui.update { it }
        }
    }

    private fun CoroutineScope.sendCreateGoalForm(i: SendCreateGoalForm) = launch {
        val state = ui.value
        flow {
            emit(state.success(message = "Success. Feature is still tricky to implement"))
            delay(config.viewModel.recoveryTime)
            emit(state)
        }.collect {
            ui.value = it
        }
    }

    private fun showCreateGoalForm(i: ShowCreateGoalForm) {
        val form = CreateGoalForm(i.intervention.name, null) {
            onCancel { ui.removeEmphasis() }
            onSubmit { post(SendCreateGoalForm(i.intervention, it)) }
        }
        ui.update { dialog(form) }
    }

    private fun CoroutineScope.sendCreateInterventionForm(i: SendCreateInterventionForm) = launch {
        val state = ui.value
        flow {
            emit(state.loading("Creating intervention, please wait . . ."))
            api.interventions.create(i.params).await()
            emit(state.success("Intervention Successfully Created"))
            val interventions = api.interventions.all(DisbursableFilter(state.context?.uid)).await()
            emit(state.table(table = disbursablesTable(interventions)))
        }.catch {
            emit(state.failure(it) {
                onGoBack { ui.removeEmphasis() }
                onRetry { post(i) }
            })
        }.collect {
            ui.update { it }
        }
    }

    private fun CoroutineScope.showCreateInterventionForm(i: ShowCreateInterventionForm) = launch {
        val state = ui.value
        flow {
            emit(state.loading("Preparing interventions form, please wait . . ."))
            val businesses = api.businesses.all().await()
            val business = businesses.firstOrNull { it.uid == state.context?.uid }
            val form = CreateInterventionForm(
                businesses = businesses,
                business = business,
                params = i.params
            ) {
                onCancel { ui.removeEmphasis() }
                onSubmit { post(SendCreateInterventionForm(it)) }
            }
            emit(state.dialog(form))
        }.catch {
            emit(state.failure(it) {
                onGoBack { post(ShowCreateInterventionForm(null, i.params)) }
                onRetry { post(i) }
            })
        }.collect {
            ui.update { it }
        }
    }

    override fun disbursablesTable(data: List<InterventionSummary>) = tableOf(data) {
        emptyMessage = "No Interventions Found"
        emptyDetails = "You haven't created any intervention for this business"
        emptyAction("Create Intervention") { post(ShowCreateInterventionForm(null, null)) }

        primaryAction("Create Intervention") { post(ShowCreateInterventionForm(null, null)) }
        primaryAction("Refresh") { post(LoadAllDisbursables(ui.value.context?.uid)) }

        singleAction("Add Disbursement") { post(ShowDisbursementForm(it.data, null)) }
        singleAction("Add Goal") { post(ShowCreateGoalForm(it.data, null)) }

        singleAction("Edit Intervention") { post(ShowUpdateInterventionForm(it.data, null)) }
        singleAction("Delete Intervention") { post(ShowDeleteOneDisbursableDialog(it.data)) }
        multiAction("Delete All") { post(ShowDeleteManyDisbursablesDialog(it)) }

        selectable()
        InterventionsColumns(showBusinessColumn = ui.value.context == null, extended = true)
        actions("Actions") {
            action("Issue Disbursement") { post(ShowDisbursementForm(it.data, null)) }
            action("Add Goal") { post(ShowCreateGoalForm(it.data, null)) }
            action("Edit") { post(ShowUpdateInterventionForm(it.data, null)) }
            action("Delete") { post(ShowDeleteOneDisbursableDialog(it.data)) }
        }
    }
}