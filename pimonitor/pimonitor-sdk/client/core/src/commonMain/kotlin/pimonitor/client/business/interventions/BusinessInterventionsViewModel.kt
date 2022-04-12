package pimonitor.client.business.interventions

import bitframe.client.UIScopeConfig
import datetime.toLocalDateTime
import kash.Currency
import kash.MoneyFormatterOptions
import kotlinx.collections.interoperable.List
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.datetime.minus
import later.await
import pimonitor.client.PiMonitorApi
import pimonitor.client.business.interventions.BusinessInterventionsIntent.*
import pimonitor.client.business.interventions.dialogs.CreateDisbursementDialog
import pimonitor.client.business.interventions.dialogs.CreateGoalDialog
import pimonitor.client.business.interventions.dialogs.CreateInterventionDialog
import pimonitor.client.business.interventions.params.toCreateInterventionDisbursementParams
import pimonitor.client.business.interventions.params.toCreateInterventionParams
import pimonitor.core.business.interventions.Intervention
import pimonitor.core.business.utils.disbursements.toParsedParams
import presenters.cases.CrowdState
import presenters.cases.Feedback
import presenters.table.builders.tableOf
import viewmodel.ViewModel

class BusinessInterventionsViewModel(
    val config: UIScopeConfig<PiMonitorApi>
) : ViewModel<BusinessInterventionsIntent, CrowdState<Intervention>>(CrowdState(), config.viewModel) {
    private val api get() = config.service
    lateinit var businessId: String
    val currency = Currency.ZAR
    val options = MoneyFormatterOptions(abbreviate = false, decimals = 0, prefix = "")

    override fun CoroutineScope.execute(i: BusinessInterventionsIntent): Any = when (i) {
        ExitDialog -> exitDialog()
        is LoadAllInterventions -> loadAllInterventions(i)
        is ShowCreateInterventionForm -> showCreateInterventionForm(i)
        is SendCreateInterventionForm -> sendCreateInterventionForm(i)
        is ShowCreateDisbursementForm -> showCreateDisbursementForm(i)
        is SendCreateDisbursementForm -> sendCreateDisbursementForm(i)
        is ShowCreateGoalForm -> showCreateGoalForm(i)
        is SendCreateGoalForm -> sendCreateGoalForm(i)
    }

    private fun CoroutineScope.sendCreateGoalForm(i: SendCreateGoalForm) = launch {
        val state = ui.value
        flow {
            emit(state.copy(status = Feedback.Success(message = "Success. Feature is still tricky to implement")))
            delay(config.viewModel.recoveryTime)
            emit(state)
        }.collect {
            ui.value = it
        }
    }

    private fun showCreateGoalForm(i: ShowCreateGoalForm) {
        ui.value = ui.value.copy(
            dialog = CreateGoalDialog(i.intervention.name) {
                onCancel { post(ExitDialog) }
                onSubmit { post(SendCreateGoalForm(i.intervention, it)) }
            },
            focus = i.intervention
        )
    }

    private fun CoroutineScope.sendCreateDisbursementForm(i: SendCreateDisbursementForm) = launch {
        val state = ui.value
        flow {
            emit(state.copy(status = Feedback.Loading("Creating disbursement for ${i.intervention.name}"), dialog = null))
            val params = i.params.toCreateInterventionDisbursementParams(i.intervention.uid)
            api.businessInterventions.disburse(params).await()
            val amount = params.toParsedParams(currency).amount.toFormattedString(options)
            emit(state.copy(status = Feedback.Success("$amount disbursed successfully. Reloading your interventions, please wait. . ."), dialog = null))
            val interventions = api.businessInterventions.all(businessId).await()
            emit(state.copy(status = Feedback.None, table = interventionsTable(interventions), dialog = null))
        }.catch {
            emit(state.copy(status = Feedback.Failure(message = "Failed to create disbursement", cause = it), dialog = null))
            delay(config.viewModel.recoveryTime)
            emit(state)
        }.collect {
            ui.value = it
        }
    }

    private fun showCreateDisbursementForm(i: ShowCreateDisbursementForm) {
        ui.value = ui.value.copy(
            dialog = CreateDisbursementDialog(i.intervention.name) {
                onCancel { post(ExitDialog) }
                onSubmit { post(SendCreateDisbursementForm(i.intervention, it)) }
            },
            focus = i.intervention
        )
    }

    private fun exitDialog() {
        val state = ui.value
        if (state.dialog != null) {
            ui.value = ui.value.copy(dialog = null)
        }
    }

    private fun CoroutineScope.sendCreateInterventionForm(i: SendCreateInterventionForm) = launch {
        val state = ui.value
        flow {
            emit(state.copy(status = Feedback.Loading("Creating intervention, please wait . . ."), dialog = null))
            val params = i.params.toCreateInterventionParams(businessId)
            api.businessInterventions.create(params).await()
            emit(state.copy(status = Feedback.Success("Intervention Created. Reloading your interventions, please wait. . ."), dialog = null))
            val interventions = api.businessInterventions.all(businessId).await()
            emit(state.copy(status = Feedback.None, table = interventionsTable(interventions), dialog = null))
        }.catch {
            emit(state.copy(status = Feedback.Failure(message = "Failed to create an intervention", cause = it), dialog = null))
            delay(config.viewModel.recoveryTime)
            emit(state)
        }.collect {
            ui.value = it
        }
    }

    private fun CoroutineScope.showCreateInterventionForm(i: ShowCreateInterventionForm) = launch {
        val state = ui.value.copy(dialog = null)
        flow {
            emit(state.copy(status = Feedback.Loading("Preparing interventions form, please wait . . .")))
            val business = api.businesses.load(i.businessId).await()
            val dialog = CreateInterventionDialog(business.name) {
                onCancel { post(ExitDialog) }
                onSubmit { post(SendCreateInterventionForm(it)) }
            }
            emit(state.copy(status = Feedback.None, dialog = dialog))
        }.catch {
            emit(state.copy(status = Feedback.Failure(message = "Failed to show interventions", cause = it)))
            delay(config.viewModel.recoveryTime)
            emit(state)
        }.collect {
            ui.value = it
        }
    }

    private fun CoroutineScope.loadAllInterventions(i: LoadAllInterventions) = launch {
        businessId = i.businessId
        val state = ui.value.copy(dialog = null)
        flow {
            emit(state.copy(status = Feedback.Loading("Loading all interventions, please wait . . .")))
            val interventions = api.businessInterventions.all(businessId).await()
            emit(state.copy(status = Feedback.None, table = interventionsTable(interventions)))
        }.catch {
            emit(state.copy(status = Feedback.Failure(message = "Failed to load all interventions", cause = it) {
                onRetry { post(i) }
            }))
        }.collect {
            ui.value = it
        }
    }

    private fun interventionsTable(data: List<Intervention>) = tableOf(data) {
        emptyMessage = "No Interventions Found"
        emptyDetails = "You haven't created any intervention for this business"
        emptyAction("Create Intervention") { post(ShowCreateInterventionForm(businessId)) }

        primaryAction("Create Intervention") { post(ShowCreateInterventionForm(businessId)) }
        primaryAction("Refresh") { post(LoadAllInterventions(businessId)) }

        singleAction("Add Disbursement") { post(ShowCreateDisbursementForm(it.data)) }
        singleAction("Add Goal") { post(ShowCreateGoalForm(it.data)) }

        selectable()
        val dateFormat = "{DD}-{MM}-{YYYY}"
        column("Name") { it.data.name }
        column("Amount") { it.data.amount.toFormattedString(options) }
        column("Disbursed") { it.data.totalDisbursed.toFormattedString(options) }
        column("Goals") { "0/${it.data.goals.size}" }
        column("Start") { it.data.date.format(dateFormat) }
        column("Deadline") { it.data.deadline.format(dateFormat) }
        column("Countdown") { (it.data.deadline - it.data.date).toString() }
        column("Created By") { it.data.createdBy.name }
        actionsColumn("Actions") {
            action("Issue Disbursement") { post(ShowCreateDisbursementForm(it.data)) }
            action("Add Goal") { post(ShowCreateGoalForm(it.data)) }
        }
    }
}