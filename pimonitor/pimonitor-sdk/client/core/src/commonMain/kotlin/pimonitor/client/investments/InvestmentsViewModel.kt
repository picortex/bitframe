package pimonitor.client.investments

import bitframe.client.UIScopeConfig
import kash.MoneyFormatterOptions
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import later.await
import pimonitor.client.PiMonitorApi
import pimonitor.client.business.investments.params.toCreateInvestmentDisbursementParams
import pimonitor.client.business.utils.disbursements.CreateDisbursementForm
import pimonitor.client.investments.InvestmentIntent.*
import pimonitor.core.investments.InvestmentSummary
import presenters.cases.CrowdState
import presenters.cases.Feedback
import presenters.modal.confirmDialog
import presenters.modal.dialog
import presenters.table.builders.tableOf
import viewmodel.ViewModel

class InvestmentsViewModel(private val config: UIScopeConfig<PiMonitorApi>) : ViewModel<InvestmentIntent, CrowdState<InvestmentSummary>>(CrowdState()) {
    private val api get() = config.service
    override fun CoroutineScope.execute(i: InvestmentIntent): Any = when (i) {
        is LoadAllInvestments -> loadAllInvestments(i)
        is ShowCreateInvestmentForm -> TODO()
        is SendCreateInvestmentForm -> TODO()
        is ShowEditInvestmentForm -> TODO()
        is SendEditInvestmentForm -> sendEditInvestment(i)
        is ShowDisbursementForm -> showDisbursementForm(i)
        is SendDisbursementForm -> sendDisbursementForm(i)
        is ShowDeleteOneInvestmentDialog -> showDeleteOneInvestment(i)
        is SendDeleteOneInvestmentIntent -> sendDeleteOneInvestment(i)
        is ShowDeleteManyInvestmentsDialog -> showDeleteManyInvestmentsDialog(i)
        is SendDeleteManyInvestmentsIntent -> sendDeleteManyInvestments(i)
    }

    private fun CoroutineScope.sendEditInvestment(i: SendEditInvestmentForm) = launch {
        val state = ui.value
        flow {
            emit(state.copy(status = Feedback.Loading("Editing ${i.investment.name}, please wait . . .!")))
        }.catch {
            emit(state.copy(status = Feedback.Failure(it) {
                onCancel { post(ShowEditInvestmentForm(i.investment)) }
                onRetry { post(i) }
            }))
        }.collect {
            ui.value = it
        }
    }

    private fun showDisbursementForm(i: ShowDisbursementForm) {
        val state = ui.value
        val form = CreateDisbursementForm(i.investment.name, i.params) {
            onCancel { ui.value = state }
            onSubmit { params -> post(SendDisbursementForm(i.investment, params)) }
        }
        ui.value = state.copy(dialog = dialog(form))
    }

    private fun CoroutineScope.sendDisbursementForm(i: SendDisbursementForm) = launch {
        val state = ui.value
        flow {
            emit(state.copy(status = Feedback.Loading("Sending disbursement, please wait. . .!")))
            val disbursement = api.investments.disburse(i.params.toCreateInvestmentDisbursementParams(i.investment.uid)).await()
            emit(state.copy(status = Feedback.Success("${disbursement.amount.toFormattedString()} has been successfully disbursed to ${i.investment.name} investment. Loading the remaining investments, please wait. . .")))
            emit(state.copy(status = Feedback.None, table = investmentsTable(api.investments.all().await())))
        }.catch {
            emit(state.copy(status = Feedback.Failure(it) {
                onGoBack { post(ShowDisbursementForm(i.investment, i.params)) }
                onRetry { post(i) }
            }))
        }.collect {
            ui.value = it
        }
    }

    private fun showDeleteOneInvestment(i: ShowDeleteOneInvestmentDialog) {
        val state = ui.value
        ui.value = state.copy(
            dialog = confirmDialog("Delete Investment", "Are you sure you want to delete ${i.investment.name} investment?") {
                onCancel { ui.value = state }
                onConfirm { post(SendDeleteOneInvestmentIntent(i.investment)) }
            }
        )
    }

    private fun CoroutineScope.sendDeleteOneInvestment(i: SendDeleteOneInvestmentIntent) = launch {
        val state = ui.value.copy(dialog = null)
        flow {
            emit(state.copy(status = Feedback.Loading("Deleting ${i.investment.name} investment, please wait . . .")))
            api.investments.delete(i.investment.uid).await()
            emit(state.copy(status = Feedback.Success("Investment ${i.investment.name} deleted. Loading the remaining investments, please wait. . .")))
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
            emit(state.copy(status = Feedback.Success("${i.investments.size} Investments deleted. Loading the remaining investments, please wait. . .")))
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
        singleAction("Issue Disbursement") { post(ShowDisbursementForm(it.data, null)) }
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
            action("Issue Disbursement") { post(ShowDisbursementForm(it.data, null)) }
            action("Edit") { post(ShowEditInvestmentForm(it.data)) }
            action("Delete") { post(ShowDeleteOneInvestmentDialog(it.data)) }
        }
    }
}