package pimonitor.client.investments

import bitframe.client.UIScopeConfig
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import later.await
import pimonitor.client.PiMonitorApi
import pimonitor.client.investments.InvestmentsIntent.*
import pimonitor.client.investments.forms.CreateInvestmentForm
import pimonitor.client.investments.forms.UpdateInvestmentForm
import pimonitor.client.utils.disbursable.disbursements.forms.CreateDisbursementForm
import pimonitor.client.utils.live.update
import pimonitor.client.utils.money.toDefaultFormat
import pimonitor.core.investments.InvestmentSummary
import pimonitor.core.investments.filters.InvestmentFilter
import pimonitor.core.investments.params.toIdentifiedParams
import pimonitor.core.investments.params.toValidatedParams
import pimonitor.core.utils.disbursables.disbursements.params.toValidatedDisbursableParams
import presenters.cases.CentralState
import presenters.cases.Emphasis.Companion.Dialog
import presenters.cases.Emphasis.Companion.Failure
import presenters.cases.Emphasis.Companion.Loading
import presenters.cases.Emphasis.Companion.Success
import presenters.modal.confirmDialog
import presenters.table.builders.tableOf
import viewmodel.ViewModel

class InvestmentsViewModel(
    private val config: UIScopeConfig<PiMonitorApi>
) : ViewModel<InvestmentsIntent, CentralState<InvestmentSummary>>(CentralState(Loading("Loading"))) {
    private val api get() = config.service
    private var businessId: String? = null
    override fun CoroutineScope.execute(i: InvestmentsIntent): Any = when (i) {
        is LoadAllInvestments -> loadAllInvestments(i)
        is ShowCreateInvestmentForm -> showCreateInvestmentForm(i)
        is SendCreateInvestmentForm -> sendCreateInvestmentForm(i)
        is ShowUpdateInvestmentForm -> showUpdateInvestmentForm(i)
        is SendUpdateInvestmentForm -> sendUpdateInvestment(i)
        is ShowDisbursementForm -> showDisbursementForm(i)
        is SendDisbursementForm -> sendDisbursementForm(i)
        is ShowDeleteOneInvestmentDialog -> showDeleteOneInvestment(i)
        is SendDeleteOneInvestmentIntent -> sendDeleteOneInvestment(i)
        is ShowDeleteManyInvestmentsDialog -> showDeleteManyInvestmentsDialog(i)
        is SendDeleteManyInvestmentsIntent -> sendDeleteManyInvestments(i)
    }

    private fun CoroutineScope.showCreateInvestmentForm(i: ShowCreateInvestmentForm) = launch {
        val state = ui.value
        flow {
            emit(state.copy(emphasis = Loading("Preparing investment form, please wait. . .")))
            val businesses = api.businesses.all().await()
            val business = businesses.firstOrNull { it.uid == businessId }
            val form = CreateInvestmentForm(businesses, business, i.params) {
                onCancel { ui.value = state }
                onSubmit { params -> post(SendCreateInvestmentForm(params)) }
            }
            emit(state.copy(emphasis = Dialog(form)))
        }.catch {
            emit(state.copy(emphasis = Failure(it) {
                onGoBack { ui.value = state }
                onRetry { post(i) }
            }))
        }.collect {
            ui.value = it
        }
    }

    private fun CoroutineScope.sendCreateInvestmentForm(i: SendCreateInvestmentForm) = launch {
        val state = ui.value
        flow {
            emit(state.copy(emphasis = Loading("Capturing ${i.params.name} investment, please wait . . .!")))
            val params = i.params.toValidatedParams()
            val investment = api.investments.create(params).await()
            emit(state.copy(emphasis = Success("${investment.name} investment has been created successfully. Updating your feed, please wait. . .")))
            emit(state.copy(table = investmentsTable(api.investments.all(InvestmentFilter(businessId)).await())))
        }.catch {
            emit(state.copy(emphasis = Failure(it) {
                onCancel { post(ShowCreateInvestmentForm(null, i.params)) }
                onRetry { post(i) }
            }))
        }.collect {
            ui.value = it
        }
    }

    private fun CoroutineScope.showUpdateInvestmentForm(i: ShowUpdateInvestmentForm) = launch {
        val state = ui.value
        flow {
            emit(state.copy(emphasis = Loading("Preparing investment form, please wait. . .")))
            val businesses = api.businesses.all().await()
            val form = UpdateInvestmentForm(businesses, i.investment, i.params) {
                onCancel { ui.value = state }
                onSubmit { params -> post(SendUpdateInvestmentForm(i.investment, params)) }
            }
            emit(state.copy(emphasis = Dialog(form)))
        }.catch {
            emit(state.copy(emphasis = Failure(it) {
                onGoBack { ui.value = state }
                onRetry { post(i) }
            }))
        }.collect {
            ui.value = it
        }
    }

    private fun CoroutineScope.sendUpdateInvestment(i: SendUpdateInvestmentForm) = launch {
        val state = ui.value
        flow {
            emit(state.copy(emphasis = Loading("Editing ${i.investment.name}, please wait . . .!")))
            val params = i.params.toIdentifiedParams(i.investment.uid)
            val investment = api.investments.update(params).await()
            emit(state.copy(emphasis = Success("${investment.name} investment has been updated successfully. Synchronizing the remaining investments, please wait. . .")))
            emit(state.copy(table = investmentsTable(api.investments.all(InvestmentFilter(businessId)).await())))
        }.catch {
            emit(state.copy(emphasis = Failure(it) {
                onCancel { post(ShowUpdateInvestmentForm(i.investment, i.params)) }
                onRetry { post(i) }
            }))
        }.collect {
            ui.value = it
        }
    }

    private fun showDisbursementForm(i: ShowDisbursementForm) {
        val state = ui.value
        val form = CreateDisbursementForm(i.investment, i.params) {
            onCancel { ui.value = state }
            onSubmit { params -> post(SendDisbursementForm(i.investment, params)) }
        }
        ui.value = state.copy(emphasis = Dialog(form))
    }

    private fun CoroutineScope.sendDisbursementForm(i: SendDisbursementForm) = launch {
        val state = ui.value
        flow {
            emit(state.copy(emphasis = Loading("Sending disbursement, please wait. . .!")))
            val disbursement = api.investments.createDisbursement(i.params.toValidatedDisbursableParams(i.investment.uid)).await()
            emit(state.copy(emphasis = Success("${disbursement.amount.toFormattedString()} has been successfully disbursed to ${i.investment.name} investment. Loading the remaining investments, please wait. . .")))
            emit(state.copy(table = investmentsTable(api.investments.all(InvestmentFilter(businessId)).await())))
        }.catch {
            emit(state.copy(emphasis = Failure(it) {
                onGoBack { post(ShowDisbursementForm(i.investment, i.params)) }
                onRetry { post(i) }
            }))
        }.collect {
            ui.value = it
        }
    }

    private fun showDeleteOneInvestment(i: ShowDeleteOneInvestmentDialog) {
        val state = ui.value
        val confirm = confirmDialog("Delete Investment", "Are you sure you want to delete ${i.investment.name} investment?") {
            onCancel { ui.value = state }
            onConfirm { post(SendDeleteOneInvestmentIntent(i.investment)) }
        }
        ui.value = state.copy(emphasis = Dialog(confirm))
    }

    private fun CoroutineScope.sendDeleteOneInvestment(i: SendDeleteOneInvestmentIntent) = launch {
        val state = ui.value
        flow {
            emit(state.copy(emphasis = Loading("Deleting ${i.investment.name} investment, please wait . . .")))
            api.investments.delete(i.investment.uid).await()
            emit(state.copy(emphasis = Success("Investment ${i.investment.name} deleted. Loading the remaining investments, please wait. . .")))
            emit(state.copy(table = investmentsTable(api.investments.all(InvestmentFilter(businessId)).await())))
        }.catch {
            emit(state.copy(emphasis = Failure(it) {
                onGoBack { ui.value = state }
                onRetry { post(i) }
            }))
        }.collect {
            ui.value = it
        }
    }

    private fun showDeleteManyInvestmentsDialog(i: ShowDeleteManyInvestmentsDialog) {
        val state = ui.value
        val confirm = confirmDialog("Delete Investments", "Are you sure you want to delete ${i.investments.size} investments?") {
            onCancel { ui.value = state }
            onConfirm { post(SendDeleteManyInvestmentsIntent(i.investments.map { it.data }.toTypedArray())) }
        }
        ui.update { copy(emphasis = Dialog(confirm)) }
    }

    private fun CoroutineScope.sendDeleteManyInvestments(i: SendDeleteManyInvestmentsIntent) = launch {
        val state = ui.value
        flow {
            emit(state.copy(emphasis = Loading("Deleting ${i.investments.size} investments, please wait. . .")))
            api.investments.delete(*i.investments.map { it.uid }.toTypedArray()).await()
            emit(state.copy(emphasis = Success("${i.investments.size} Investments deleted. Loading the remaining investments, please wait. . .")))
            emit(state.copy(table = investmentsTable(api.investments.all(InvestmentFilter(businessId)).await())))
        }.catch {
            emit(state.copy(emphasis = Failure(it) {
                onGoBack { ui.value = state }
                onRetry { post(i) }
            }))
        }.collect {
            ui.value = it
        }
    }

    private fun CoroutineScope.loadAllInvestments(i: LoadAllInvestments) = launch {
        businessId = i.businessId
        val state = ui.value
        flow {
            emit(state.copy(emphasis = Loading("Loading your investments, please wait. . .")))
            emit(state.copy(table = investmentsTable(api.investments.all(InvestmentFilter(businessId)).await())))
        }.catch {
            emit(state.copy(emphasis = Failure(it) {
                onRetry { post(i) }
            }))
        }.collect {
            ui.value = it
        }
    }

    private fun investmentsTable(data: List<InvestmentSummary>) = tableOf(data) {
        emptyMessage = "No Investment Found"
        emptyDetails = "You haven't captured any investments"
        emptyAction("Capture Investment") { post(ShowCreateInvestmentForm(null, null)) }

        primaryAction("Add Investment") { post(ShowCreateInvestmentForm(null, null)) }
        primaryAction("Refresh") { post(LoadAllInvestments(businessId)) }
        singleAction("Issue Disbursement") { post(ShowDisbursementForm(it.data, null)) }
        singleAction("Edit Investment") { post(ShowUpdateInvestmentForm(it.data, null)) }
        singleAction("Delete Investment") { post(ShowDeleteOneInvestmentDialog(it.data)) }
        multiAction("Delete All") { post(ShowDeleteManyInvestmentsDialog(it)) }
        selectable()
        column("Name") { it.data.name }
        if (businessId == null) column("Business") { it.data.businessName }
        column("Source") { it.data.source }
        column("Type") { it.data.type }
        column("Amount") { it.data.amount.toDefaultFormat() }
        column("Disbursed") { it.data.totalDisbursed.toDefaultFormat() }
        column("Progress") { "${it.data.disbursementProgressInPercentage.asInt}%" }
        column("Created By") { it.data.createdBy.name }
        actions("Actions") {
            action("Issue Disbursement") { post(ShowDisbursementForm(it.data, null)) }
            action("Edit") { post(ShowUpdateInvestmentForm(it.data, null)) }
            action("Delete") { post(ShowDeleteOneInvestmentDialog(it.data)) }
        }
    }
}