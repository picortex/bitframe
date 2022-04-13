package pimonitor.client.investments

import bitframe.client.UIScopeConfig
import kash.MoneyFormatterOptions
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import later.await
import pimonitor.client.PiMonitorApi
import pimonitor.client.investments.InvestmentIntent.*
import pimonitor.core.investments.InvestmentSummary
import presenters.cases.CrowdState
import presenters.cases.Feedback
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
        is ShowDeleteOneInvestmentDialog -> TODO()
        is SendDeleteOneInvestmentIntent -> TODO()
        is ShowDeleteManyInvestmentDialog -> TODO()
        is SendDeleteManyInvestmentIntent -> TODO()
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
        multiAction("Delete All") { post(ShowDeleteManyInvestmentDialog(it)) }
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