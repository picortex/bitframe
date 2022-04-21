package pimonitor.client.interventions

import bitframe.client.UIScopeConfig
import bitframe.client.map
import bitframe.core.Identified
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import later.await
import pimonitor.client.PiMonitorApi
import pimonitor.client.interventions.InterventionsIntent.*
import pimonitor.client.interventions.forms.CreateGoalForm
import pimonitor.client.interventions.forms.CreateInterventionForm
import pimonitor.client.interventions.forms.UpdateInterventionForm
import pimonitor.client.utils.disbursables.DisbursablesIntent
import pimonitor.client.utils.disbursables.DisbursablesIntent.*
import pimonitor.client.utils.disbursables.DisbursablesViewModel
import pimonitor.client.utils.live.removeEmphasis
import pimonitor.client.utils.live.update
import pimonitor.client.utils.money.toDefaultFormat
import pimonitor.core.interventions.InterventionSummary
import pimonitor.core.utils.disbursables.filters.DisbursableFilter
import presenters.cases.Emphasis
import presenters.cases.Emphasis.Companion.Dialog
import presenters.cases.Emphasis.Companion.Failure
import presenters.cases.Emphasis.Companion.Loading
import presenters.cases.Emphasis.Companion.Success
import presenters.table.builders.tableOf

class InterventionsViewModel(
    val config: UIScopeConfig<PiMonitorApi>
) : DisbursablesViewModel<InterventionSummary>(config, config.service.interventions) {
    override fun CoroutineScope.execute(i: DisbursablesIntent): Any = when (i) {
        is ShowCreateInterventionForm -> showCreateInterventionForm(i)
        is SendCreateInterventionForm -> sendCreateInterventionForm(i)
        is ShowUpdateInterventionForm -> showUpdateInterventionForm(i)
        is SendUpdateInterventionForm -> sendUpdateInterventionForm(i)
        is ShowCreateGoalForm -> showCreateGoalForm(i)
        is SendCreateGoalForm -> sendCreateGoalForm(i)
        is ShowUpdateGoalForm -> TODO()
        is SendUpdateGoalForm -> TODO()
        else -> perform(i)
    }

    private fun CoroutineScope.showUpdateInterventionForm(i: ShowUpdateInterventionForm) = launch {
        val state = ui.value
        flow {
            emit(state.copy(emphasis = Loading("Preparing interventions form, please wait . . .")))
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
            emit(state.copy(emphasis = Dialog(form)))
        }.catch {
            emit(state.copy(emphasis = Failure(it) {
                onGoBack { post(ShowCreateInterventionForm(null, i.params)) }
                onRetry { post(i) }
            }))
        }.collect {
            ui.update { it }
        }
    }

    private fun CoroutineScope.sendUpdateInterventionForm(i: SendUpdateInterventionForm) = launch {
        val state = ui.value
        flow {
            emit(state.copy(emphasis = Loading("Updating investment, please wait. . .!")))
            val params = Identified(i.intervention.uid, i.params)
            val intervention = api.interventions.update(params).await()
            emit(state.copy(emphasis = Success("Updated ${intervention.name} investment")))
            val interventions = api.interventions.all(DisbursableFilter(state.context?.uid)).await()
            emit(state.copy(table = disbursablesTable(interventions)))
        }.catch {
            emit(state.copy(emphasis = Failure(it) {
                onGoBack { post(ShowUpdateInterventionForm(i.intervention, i.params)) }
                onRetry { post(i) }
            }))
        }.collect {
            ui.update { it }
        }
    }

    private fun CoroutineScope.sendCreateGoalForm(i: SendCreateGoalForm) = launch {
        val state = ui.value
        flow {
            emit(state.copy(emphasis = Success(message = "Success. Feature is still tricky to implement")))
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
        ui.update { copy(emphasis = Dialog(form)) }
    }

    private fun CoroutineScope.sendCreateInterventionForm(i: SendCreateInterventionForm) = launch {
        val state = ui.value
        flow {
            emit(state.copy(emphasis = Loading("Creating intervention, please wait . . .")))
            api.interventions.create(i.params).await()
            emit(state.copy(emphasis = Success("Intervention Successfully Created")))
            val interventions = api.interventions.all(DisbursableFilter(state.context?.uid)).await()
            emit(state.copy(table = disbursablesTable(interventions)))
        }.catch {
            emit(state.copy(emphasis = Failure(it) {
                onGoBack { ui.removeEmphasis() }
                onRetry { post(i) }
            }))
        }.collect {
            ui.update { it }
        }
    }

    private fun CoroutineScope.showCreateInterventionForm(i: ShowCreateInterventionForm) = launch {
        val state = ui.value
        flow {
            emit(state.copy(emphasis = Loading("Preparing interventions form, please wait . . .")))
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
            emit(state.copy(emphasis = Dialog(form)))
        }.catch {
            emit(state.copy(emphasis = Failure(it) {
                onGoBack { post(ShowCreateInterventionForm(null, i.params)) }
                onRetry { post(i) }
            }))
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
        val dateFormat = "{DD}-{MM}-{YYYY}"
        column("Name") { it.data.name }
        if (ui.value.context == null) column("Business") { it.data.businessName }
        column("Amount") { it.data.amount.toDefaultFormat() }
        column("Disbursed") { it.data.totalDisbursed.toDefaultFormat() }
        column("Goals") { "0/${it.data.goals.size}" }
        column("Start") { it.data.date.format(dateFormat) }
        column("Deadline") { it.data.deadline.format(dateFormat) }
        column("Countdown") { (it.data.deadline - it.data.date).toString() }
        column("Created By") { it.data.createdBy.name }
        actions("Actions") {
            action("Issue Disbursement") { post(ShowDisbursementForm(it.data, null)) }
            action("Add Goal") { post(ShowCreateGoalForm(it.data, null)) }
            action("Edit") { post(ShowUpdateInterventionForm(it.data, null)) }
            action("Delete") { post(ShowDeleteOneDisbursableDialog(it.data)) }
        }
    }
}