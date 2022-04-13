package pimonitor.client.investments

import bitframe.client.UIScopeConfig
import kash.MoneyFormatterOptions
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import later.await
import pimonitor.client.PiMonitorApi
import pimonitor.client.investments.InvestmentIntent.*
import pimonitor.core.investments.InvestmentSummary
import presenters.cases.CrowdState
import presenters.cases.Feedback
import presenters.modal.confirmDialog
import presenters.table.builders.tableOf
import viewmodel.ViewModel

class InvestmentsViewModel(private val config: UIScopeConfig<PiMonitorApi>) : ViewModel<InvestmentIntent, CrowdState<InvestmentSummary>>(CrowdState()) {
    private val api get() = config.service
    override fun CoroutineScope.execute(i: InvestmentIntent): Any = when (i) {
        is LoadAllInvestments -> loadAllInvestments(i)
        is ShowCreateInvestmentForm -> TODO()
        is SendCreateInvestmentForm -> TODO()
        is ShowEditInvestmentForm -> TODO()
        is SendEditInvestmentForm -> TODO()
        is ShowDisbursementForm -> TODO()
        is SendDisbursementForm -> TODO()
        is ShowDeleteOneInvestmentDialog -> showDeleteOneInvestment(i)
        is SendDeleteOneInvestmentIntent -> sendDeleteOneInvestment(i)
        is ShowDeleteManyInvestmentsDialog -> showDeleteManyInvestmentsDialog(i)
        is SendDeleteManyInvestmentsIntent -> sendDeleteManyInvestments(i)
    }

    private fun showDeleteOneInvestment(i: ShowDeleteOneInvestmentDialog) {

    }

    private fun CoroutineScope.sendDeleteOneInvestment(i: SendDeleteOneInvestmentIntent) = launch {
        val state = ui.value.copy(dialog = null)
        flow {
            emit(state.copy(status = Feedback.Loading("Deleting ${i.investment.name} investment, please wait . . .")))
            api.investments.delete(i.investment.uid).await()
            emit(state.copy(status = Feedback.None, table = investmentsTable(api.investments.all().await())))
        }.catch {
            emit(state.copy(status = Feedback.Failure(it) {
                onGoBack { ui.value = state }
                onRetry { post(i) }
            }))
        }.collect {
            ui.value = it
        }
    }

    private fun showDeleteManyInvestmentsDialog(i: ShowDeleteManyInvestmentsDialog) {
        val state = ui.value
        ui.value = state.copy(
            dialog = confirmDialog("Delete Investments", "Are you sure you want to delete ${i.investments.size} investments?") {
                onCancel { ui.value = state }
                onConfirm { post(SendDeleteManyInvestmentsIntent(i.investments.map { it.data }.toTypedArray())) }
            }
        )
    }

    private fun CoroutineScope.sendDeleteManyInvestments(i: SendDeleteManyInvestmentsIntent) = launch {
        val state = ui.value.copy(dialog = null)
        flow {
            emit(state.copy(status = Feedback.Loading("Deleting ${i.investments.size} investments, please wait. . .")))
            api.investments.delete(*i.investments.map { it.uid }.toTypedArray()).await()
            emit(state.copy(status = Feedback.None, table = investmentsTable(api.investments.all().await())))
        }.catch {
            emit(state.copy(status = Feedback.Failure(it) {
                onGoBack { ui.value = state }
                onRetry { post(i) }
            }))
        }.collect {
            ui.value = it
        }
    }

    private fun CoroutineScope.loadAllInvestments(i: LoadAllInvestments) = launch {
        val state = ui.value.copy(dialog = null)
        flow {
            emit(state.copy(status = Feedback.Loading("Loading your investments, please wait. . .")))
            emit(state.copy(status = Feedback.None, table = investmentsTable(api.investments.all().await())))
        }.catch {
            emit(state.copy(status = Feedback.Failure(it) {
                onRetry { post(i) }
            }))
        }.collect {
            ui.value = it
        }
    }

    private fun investmentsTable(data: List<InvestmentSummary>) = tableOf(data) {
        emptyMessage = "No Investment Found"
        emptyDetails = "You haven't captured any investments"
        emptyAction("Capture Investment") { post(ShowCreateInvestmentForm(null)) }

        primaryAction("Add Investment") { post(ShowCreateInvestmentForm(null)) }
        primaryAction("Refresh") { post(LoadAllInvestments) }
        singleAction("Issue Disbursement") { post(ShowDisbursementForm(it.data)) }
        singleAction("Edit Investment") { post(ShowEditInvestmentForm(it.data)) }
        singleAction("Delete Investment") { post(ShowDeleteOneInvestmentDialog(it.data)) }
        multiAction("Delete All") { post(ShowDeleteManyInvestmentsDialog(it)) }
        selectable()
        column("Name") { it.data.name }
        column("Business") { it.data.businessName }
        column("Source") { it.data.source }
        column("Type") { it.data.type }
        val options = MoneyFormatterOptions(decimals = 0, abbreviate = false)
        column("Amount") {
            it.data.amount.toFormattedString(options)
        }
        column("Disbursed") {
            it.data.totalDisbursed.toFormattedString(options)
        }
        column("Progress") {
            "${it.data.disbursementProgressInPercentage.asInt}%"
        }
        column("Created By") { it.data.createdBy.name }
        actions("Actions") {
            action("Issue Disbursement") { post(ShowDisbursementForm(it.data)) }
            action("Edit") { post(ShowEditInvestmentForm(it.data)) }
            action("Delete") { post(ShowDeleteOneInvestmentDialog(it.data)) }
        }
    }
}