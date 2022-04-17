package pimonitor.client.utils.disbursables

import bitframe.client.UIScopeConfig
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import later.await
import pimonitor.client.PiMonitorApi
import pimonitor.client.utils.disbursable.disbursements.forms.CreateDisbursementForm
import pimonitor.client.utils.disbursables.DisbursablesIntent.*
import pimonitor.client.utils.live.update
import pimonitor.core.disbursables.filters.InvestmentFilter
import pimonitor.core.utils.disbursables.Disbursable
import pimonitor.core.utils.disbursables.disbursements.params.toValidatedDisbursableParams
import presenters.cases.CentralState
import presenters.cases.Emphasis.Companion.Dialog
import presenters.cases.Emphasis.Companion.Failure
import presenters.cases.Emphasis.Companion.Loading
import presenters.cases.Emphasis.Companion.Success
import kotlinx.collections.interoperable.List
import pimonitor.client.utils.live.removeEmphasis
import presenters.modal.confirmDialog
import presenters.table.Table
import viewmodel.ViewModel

abstract class DisbursableViewModel<out D : Disbursable>(
    private val config: UIScopeConfig<PiMonitorApi>
) : ViewModel<DisbursablesIntent, CentralState<@UnsafeVariance D>>(CentralState(Loading("Loading")), config.viewModel) {
    private val api get() = config.service
    abstract val service: DisbursableService<D>
    private var businessId: String? = null
    override fun CoroutineScope.execute(i: DisbursablesIntent): Any = when (i) {
        is LoadAllDisbursables -> loadAllInvestments(i)
//        is ShowCreateInvestmentForm -> showCreateInvestmentForm(i)
//        is SendCreateInvestmentForm -> sendCreateInvestmentForm(i)
//        is ShowUpdateInvestmentForm -> showUpdateInvestmentForm(i)
//        is SendUpdateInvestmentForm -> sendUpdateInvestment(i)
        is ShowDisbursementForm -> showDisbursementForm(i)
        is SendDisbursementForm -> sendDisbursementForm(i)
        is ShowDeleteOneDisbursableDialog -> showDeleteOneInvestment(i)
        is SendDeleteOneDisbursableIntent -> sendDeleteOneInvestment(i)
        is ShowDeleteManyDisbursablesDialog -> showDeleteManyInvestmentsDialog(i)
        is SendDeleteManyDisbursablesIntent -> sendDeleteManyInvestments(i)
        else -> throw IllegalStateException("Invalid intent $i")
    }

    protected abstract suspend fun loadDisbursable(businessId: String): List<D>

    private fun showDisbursementForm(i: ShowDisbursementForm) {
        val state = ui.value
        val form = CreateDisbursementForm(i.disbursable, i.params) {
            onCancel { ui.value = state }
            onSubmit { params -> post(SendDisbursementForm(i.disbursable, params)) }
        }
        ui.value = state.copy(emphasis = Dialog(form))
    }

    private fun CoroutineScope.sendDisbursementForm(i: SendDisbursementForm) = launch {
        val state = ui.value
        flow {
            emit(state.copy(emphasis = Loading("Sending disbursement, please wait. . .!")))
            val disbursement = service.createDisbursement(i.params.toValidatedDisbursableParams(i.disbursable.uid)).await()
            emit(state.copy(emphasis = Success("${disbursement.amount.toFormattedString()} has been successfully disbursed to ${i.disbursable.name} disbursable. Loading the remaining disbursables, please wait. . .")))
            emit(state.copy(table = disbursablesTable(loadDisbursable(i.disbursable.uid))))
        }.catch {
            emit(state.copy(emphasis = Failure(it) {
                onGoBack { post(ShowDisbursementForm(i.disbursable, i.params)) }
                onRetry { post(i) }
            }))
        }.collect {
            ui.value = it
        }
    }

    private fun showDeleteOneInvestment(i: ShowDeleteOneDisbursableDialog) {
        val confirm = confirmDialog("Delete ${i.disbursable.name}", "Are you sure you want to delete ${i.disbursable.name}?") {
            onCancel { ui.removeEmphasis() }
            onConfirm { post(SendDeleteOneDisbursableIntent(i.disbursable)) }
        }
        ui.update { copy(emphasis = Dialog(confirm)) }
    }

    private fun CoroutineScope.sendDeleteOneInvestment(i: SendDeleteOneDisbursableIntent) = launch {
        val state = ui.value
        flow {
            emit(state.copy(emphasis = Loading("Deleting ${i.disbursable.name} disbursable, please wait . . .")))
            api.disbursables.delete(i.disbursable.uid).await()
            emit(state.copy(emphasis = Success("Investment ${i.disbursable.name} deleted. Loading the remaining disbursables, please wait. . .")))
            emit(state.copy(table = disbursablesTable(api.disbursables.all(InvestmentFilter(businessId)).await())))
        }.catch {
            emit(state.copy(emphasis = Failure(it) {
                onGoBack { ui.value = state }
                onRetry { post(i) }
            }))
        }.collect {
            ui.value = it
        }
    }

    private fun showDeleteManyInvestmentsDialog(i: ShowDeleteManyDisbursablesDialog) {
        val state = ui.value
        val confirm = confirmDialog("Delete Investments", "Are you sure you want to delete ${i.disbursables.size} disbursables?") {
            onCancel { ui.value = state }
            onConfirm { post(SendDeleteManyDisbursablesIntent(i.disbursables.map { it.data }.toTypedArray())) }
        }
        ui.update { copy(emphasis = Dialog(confirm)) }
    }

    private fun CoroutineScope.sendDeleteManyInvestments(i: SendDeleteManyDisbursablesIntent) = launch {
        val state = ui.value
        flow {
            emit(state.copy(emphasis = Loading("Deleting ${i.disbursables.size} disbursables, please wait. . .")))
            api.disbursables.delete(*i.disbursables.map { it.uid }.toTypedArray()).await()
            emit(state.copy(emphasis = Success("${i.disbursables.size} Investments deleted. Loading the remaining disbursables, please wait. . .")))
            emit(state.copy(table = disbursablesTable(api.disbursables.all(InvestmentFilter(businessId)).await())))
        }.catch {
            emit(state.copy(emphasis = Failure(it) {
                onGoBack { ui.value = state }
                onRetry { post(i) }
            }))
        }.collect {
            ui.value = it
        }
    }

    private fun CoroutineScope.loadAllInvestments(i: LoadAllDisbursables) = launch {
        businessId = i.businessId
        val state = ui.value
        flow {
            emit(state.copy(emphasis = Loading("Loading your disbursables, please wait. . .")))
            emit(state.copy(table = disbursablesTable(api.disbursables.all(InvestmentFilter(businessId)).await())))
        }.catch {
            emit(state.copy(emphasis = Failure(it) {
                onRetry { post(i) }
            }))
        }.collect {
            ui.value = it
        }
    }

    protected abstract fun disbursablesTable(data: List<@UnsafeVariance D>): Table<@UnsafeVariance D>
}