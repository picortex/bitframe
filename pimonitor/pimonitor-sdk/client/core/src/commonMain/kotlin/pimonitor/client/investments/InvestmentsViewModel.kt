package pimonitor.client.investments

import bitframe.client.UIScopeConfig
import bitframe.client.map
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import later.await
import pimonitor.client.PiMonitorApi
import pimonitor.client.investments.InvestmentsIntent.*
import pimonitor.client.investments.forms.CreateInvestmentForm
import pimonitor.client.investments.forms.UpdateInvestmentForm
import pimonitor.client.utils.disbursables.DisbursablesIntent
import pimonitor.client.utils.disbursables.DisbursablesIntent.*
import pimonitor.client.utils.disbursables.DisbursablesViewModel
import pimonitor.client.utils.live.removeEmphasis
import pimonitor.client.utils.live.update
import pimonitor.client.utils.money.toDefaultFormat
import pimonitor.core.investments.InvestmentSummary
import pimonitor.core.investments.InvestmentsColumns
import pimonitor.core.investments.params.toIdentifiedParams
import pimonitor.core.investments.params.toValidatedParams
import pimonitor.core.utils.disbursables.filters.DisbursableFilter
import presenters.cases.Emphasis.Companion.Dialog
import presenters.cases.Emphasis.Companion.Failure
import presenters.cases.Emphasis.Companion.Loading
import presenters.cases.Emphasis.Companion.Success
import presenters.table.builders.tableOf

class InvestmentsViewModel(
    private val config: UIScopeConfig<PiMonitorApi>
) : DisbursablesViewModel<InvestmentSummary>(config, config.service.investments) {
    override fun CoroutineScope.execute(i: DisbursablesIntent): Any = when (i) {
        is ShowCreateInvestmentForm -> showCreateInvestmentForm(i)
        is SendCreateInvestmentForm -> sendCreateInvestmentForm(i)
        is ShowUpdateInvestmentForm -> showUpdateInvestmentForm(i)
        is SendUpdateInvestmentForm -> sendUpdateInvestment(i)
        else -> perform(i)
    }

    private fun CoroutineScope.showCreateInvestmentForm(i: ShowCreateInvestmentForm) = launch {
        val state = ui.value
        flow {
            emit(state.copy(emphasis = Loading("Preparing investment form, please wait. . .")))
            val businesses = api.businesses.all().await()
            val business = businesses.firstOrNull { it.uid == state.context?.uid }
            val form = CreateInvestmentForm(businesses, business, i.params) {
                onCancel { ui.removeEmphasis() }
                onSubmit { params -> post(SendCreateInvestmentForm(params)) }
            }
            emit(state.copy(emphasis = Dialog(form)))
        }.catch {
            emit(state.copy(emphasis = Failure(it) {
                onGoBack { ui.value = state }
                onRetry { post(i) }
            }))
        }.collect {
            ui.update { it }
        }
    }

    private fun CoroutineScope.sendCreateInvestmentForm(i: SendCreateInvestmentForm) = launch {
        val state = ui.value
        flow {
            emit(state.copy(emphasis = Loading("Capturing ${i.params.name} investment, please wait . . .!")))
            val params = i.params.toValidatedParams()
            val investment = api.investments.create(params).await()
            emit(state.copy(emphasis = Success("${investment.name} investment has been created successfully. Updating your feed, please wait. . .")))
            emit(state.copy(table = disbursablesTable(api.investments.all(DisbursableFilter(state.context?.uid)).await())))
        }.catch {
            emit(state.copy(emphasis = Failure(it) {
                onCancel { post(ShowCreateInvestmentForm(null, i.params)) }
                onRetry { post(i) }
            }))
        }.collect {
            ui.update { it }
        }
    }

    private fun CoroutineScope.showUpdateInvestmentForm(i: ShowUpdateInvestmentForm) = launch {
        val state = ui.value
        flow {
            emit(state.copy(emphasis = Loading("Preparing investment form, please wait. . .")))
            val businesses = api.businesses.all().await()
            val form = UpdateInvestmentForm(businesses, i.investment, i.params) {
                onCancel { ui.removeEmphasis() }
                onSubmit { params -> post(SendUpdateInvestmentForm(i.investment, params)) }
            }
            emit(state.copy(emphasis = Dialog(form)))
        }.catch {
            emit(state.copy(emphasis = Failure(it) {
                onGoBack { ui.value = state }
                onRetry { post(i) }
            }))
        }.collect {
            ui.update { it }
        }
    }

    private fun CoroutineScope.sendUpdateInvestment(i: SendUpdateInvestmentForm) = launch {
        val state = ui.value
        flow {
            emit(state.copy(emphasis = Loading("Editing ${i.investment.name}, please wait . . .!")))
            val params = i.params.toIdentifiedParams(i.investment.uid)
            val investment = api.investments.update(params).await()
            emit(state.copy(emphasis = Success("${investment.name} investment has been updated successfully. Synchronizing the remaining investments, please wait. . .")))
            emit(state.copy(table = disbursablesTable(api.investments.all(DisbursableFilter(state.context?.uid)).await())))
        }.catch {
            emit(state.copy(emphasis = Failure(it) {
                onCancel { post(ShowUpdateInvestmentForm(i.investment, i.params)) }
                onRetry { post(i) }
            }))
        }.collect {
            ui.update { it }
        }
    }

    override fun disbursablesTable(data: List<InvestmentSummary>) = tableOf(data) {
        emptyMessage = "No Investment Found"
        emptyDetails = "You haven't captured any investments"
        emptyAction("Capture Investment") { post(ShowCreateInvestmentForm(null, null)) }

        primaryAction("Add Investment") { post(ShowCreateInvestmentForm(null, null)) }
        primaryAction("Refresh") { post(LoadAllDisbursables(ui.value.context?.uid)) }
        singleAction("Issue Disbursement") { post(ShowDisbursementForm(it.data, null)) }
        singleAction("Edit Investment") { post(ShowUpdateInvestmentForm(it.data, null)) }
        singleAction("Delete Investment") { post(ShowDeleteOneDisbursableDialog(it.data)) }
        multiAction("Delete All") { post(ShowDeleteManyDisbursablesDialog(it)) }
        selectable()
        InvestmentsColumns(showBusinessColumn = ui.value.context == null)
        actions("Actions") {
            action("Issue Disbursement") { post(ShowDisbursementForm(it.data, null)) }
            action("Edit") { post(ShowUpdateInvestmentForm(it.data, null)) }
            action("Delete") { post(ShowDeleteOneDisbursableDialog(it.data)) }
        }
    }
}