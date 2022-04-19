package pimonitor.client.interventions

import bitframe.client.UIScopeConfig
import bitframe.client.map
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import later.await
import pimonitor.client.PiMonitorApi
import pimonitor.client.interventions.InterventionsIntent.*
import pimonitor.client.interventions.forms.CreateGoalForm
import pimonitor.client.interventions.forms.CreateInterventionForm
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
import presenters.table.builders.tableOf

class InterventionsViewModel(
    val config: UIScopeConfig<PiMonitorApi>
) : DisbursablesViewModel<InterventionSummary>(config.map { it.interventions }) {
    private val api get() = config.service

    override fun CoroutineScope.execute(i: DisbursablesIntent): Any = when (i) {
        is ShowCreateInterventionForm -> showCreateInterventionForm(i)
        is SendCreateInterventionForm -> sendCreateInterventionForm(i)
        is ShowUpdateInterventionForm -> TODO()
        is SendUpdateInterventionForm -> TODO()
        is ShowCreateGoalForm -> showCreateGoalForm(i)
        is SendCreateGoalForm -> sendCreateGoalForm(i)
        is ShowUpdateGoalForm -> TODO()
        is SendUpdateGoalForm -> TODO()
        else -> perform(i)
    }

    private fun CoroutineScope.sendCreateGoalForm(i: SendCreateGoalForm) = launch {
        val state = ui.value
        flow {
            emit(state.copy(emphasis = Emphasis.Success(message = "Success. Feature is still tricky to implement")))
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
            emit(state.copy(emphasis = Emphasis.Success("Intervention Successfully Created")))
            val interventions = api.interventions.all(DisbursableFilter(businessId)).await()
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
            val business = businesses.firstOrNull { it.uid == businessId }
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
        primaryAction("Refresh") { post(LoadAllDisbursables(businessId)) }

        singleAction("Add Disbursement") { post(ShowDisbursementForm(it.data, null)) }
        singleAction("Add Goal") { post(ShowCreateGoalForm(it.data, null)) }

        singleAction("Edit Intervention") { post(ShowUpdateInterventionForm(it.data, null)) }
        singleAction("Delete Intervention") { post(ShowDeleteOneDisbursableDialog(it.data)) }
        multiAction("Delete All") { post(ShowDeleteManyDisbursablesDialog(it)) }

        selectable()
        val dateFormat = "{DD}-{MM}-{YYYY}"
        column("Name") { it.data.name }
        if (businessId == null) column("Business") { it.data.businessName }
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