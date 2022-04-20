package pimonitor.client.utils.disbursables

import bitframe.client.UIScopeConfig
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import later.await
import pimonitor.client.utils.disbursable.disbursements.forms.CreateDisbursementForm
import pimonitor.client.utils.disbursables.DisbursablesIntent.*
import pimonitor.client.utils.live.removeEmphasis
import pimonitor.client.utils.live.update
import pimonitor.core.utils.disbursables.DisbursableSummary
import pimonitor.core.utils.disbursables.disbursements.params.toValidatedDisbursableParams
import pimonitor.core.utils.disbursables.filters.DisbursableFilter
import presenters.cases.CentralState
import presenters.cases.Emphasis.Companion.Dialog
import presenters.cases.Emphasis.Companion.Failure
import presenters.cases.Emphasis.Companion.Loading
import presenters.cases.Emphasis.Companion.Success
import presenters.modal.confirmDialog
import presenters.table.Table
import viewmodel.ViewModel

abstract class DisbursablesViewModel<out DS : DisbursableSummary>(
    private val config: UIScopeConfig<DisbursableService<*, DS>>
) : ViewModel<DisbursablesIntent, CentralState<@UnsafeVariance DS>>(CentralState(Loading("Loading")), config.viewModel) {
    val service: DisbursableService<*, DS> get() = config.service
    protected var businessId: String? = null
    protected fun CoroutineScope.perform(i: DisbursablesIntent): Any = when (i) {
        is LoadAllDisbursables -> loadAllDisbursables(i)
        is ShowDisbursementForm -> showDisbursementForm(i)
        is SendDisbursementForm -> sendDisbursementForm(i)
        is ShowDeleteOneDisbursableDialog -> showDeleteOneInvestment(i)
        is SendDeleteOneDisbursableIntent -> sendDeleteOneInvestment(i)
        is ShowDeleteManyDisbursablesDialog -> showDeleteManyInvestmentsDialog(i)
        is SendDeleteManyDisbursablesIntent -> sendDeleteManyInvestments(i)
        else -> throw IllegalStateException("Invalid intent $i")
    }

    private fun showDisbursementForm(i: ShowDisbursementForm) {
        val state = ui.value
        val form = CreateDisbursementForm(i.disbursable, i.params) {
            onCancel { ui.value = state }
            onSubmit { params -> post(SendDisbursementForm(i.disbursable, params)) }
        }
        ui.update { copy(emphasis = Dialog(form)) }
    }

    private fun CoroutineScope.sendDisbursementForm(i: SendDisbursementForm) = launch {
        val state = ui.value
        flow {
            emit(state.copy(emphasis = Loading("Sending disbursement, please wait. . .!")))
            val disbursement = service.createDisbursement(i.params.toValidatedDisbursableParams(i.disbursable.uid)).await()
            emit(state.copy(emphasis = Success("${disbursement.amount.toFormattedString()} has been successfully disbursed to ${i.disbursable.name} disbursable. Loading the remaining disbursables, please wait. . .")))
            emit(state.copy(table = disbursablesTable(service.all(DisbursableFilter(businessId)).await())))
        }.catch {
            emit(state.copy(emphasis = Failure(it) {
                onGoBack { post(ShowDisbursementForm(i.disbursable, i.params)) }
                onRetry { post(i) }
            }))
        }.collect {
            ui.update { it }
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
            service.delete(i.disbursable.uid).await()
            emit(state.copy(emphasis = Success("Investment ${i.disbursable.name} deleted. Loading the remaining disbursables, please wait. . .")))
            emit(state.copy(table = disbursablesTable(service.all(DisbursableFilter(businessId)).await())))
        }.catch {
            emit(state.copy(emphasis = Failure(it) {
                onGoBack { ui.removeEmphasis() }
                onRetry { post(i) }
            }))
        }.collect {
            ui.update { it }
        }
    }

    private fun showDeleteManyInvestmentsDialog(i: ShowDeleteManyDisbursablesDialog) {
        val confirm = confirmDialog(
            heading = "Delete Investments",
            details =
            "Are you sure you want to delete ${i.disbursables.size} items?"
        ) {
            onCancel { ui.removeEmphasis() }
            onConfirm { post(SendDeleteManyDisbursablesIntent(i.disbursables.map { it.data }.toTypedArray())) }
        }
        ui.update { copy(emphasis = Dialog(confirm)) }
    }

    private fun CoroutineScope.sendDeleteManyInvestments(i: SendDeleteManyDisbursablesIntent) = launch {
        val state = ui.value
        flow {
            emit(state.copy(emphasis = Loading("Deleting ${i.disbursables.size} disbursables, please wait. . .")))
            service.delete(*i.disbursables.map { it.uid }.toTypedArray()).await()
            emit(state.copy(emphasis = Success("${i.disbursables.size} Items deleted")))
            emit(state.copy(table = disbursablesTable(service.all(DisbursableFilter(businessId)).await())))
        }.catch {
            emit(state.copy(emphasis = Failure(it) {
                onGoBack { ui.removeEmphasis() }
                onRetry { post(i) }
            }))
        }.collect {
            ui.update { it }
        }
    }

    private fun CoroutineScope.loadAllDisbursables(i: LoadAllDisbursables) = launch {
        businessId = i.businessId
        val state = ui.value
        flow {
            emit(state.copy(emphasis = Loading("Loading, please wait. . .")))
            emit(state.copy(table = disbursablesTable(service.all(DisbursableFilter(businessId)).await())))
        }.catch {
            emit(state.copy(emphasis = Failure(it) {
                onRetry { post(i) }
            }))
        }.collect {
            ui.update { it }
        }
    }

    protected abstract fun disbursablesTable(data: List<@UnsafeVariance DS>): Table<@UnsafeVariance DS>
}