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
import pimonitor.client.utils.live.removeEmphasis
import pimonitor.client.utils.live.update
import pimonitor.core.businesses.MonitoredBusinessBasicInfo
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
    private val config: UIScopeConfig<PiMonitorApi>,
    val service: DisbursableService<*, DS>,
) : ViewModel<DisbursablesIntent, CentralState<MonitoredBusinessBasicInfo?, @UnsafeVariance DS>>(CentralState(Loading("Loading")), config.viewModel) {
    val api get() = config.service
    protected fun CoroutineScope.perform(i: DisbursablesIntent): Any = when (i) {
        is LoadAllDisbursables -> loadAllDisbursables(i)
        is ShowDisbursementForm -> showDisbursementForm(i)
        is SendDisbursementForm -> sendDisbursementForm(i)
        is ShowDeleteOneDisbursableDialog -> showDeleteOneDisbursable(i)
        is SendDeleteOneDisbursableIntent -> sendDeleteOneDisbursable(i)
        is ShowDeleteManyDisbursablesDialog -> showDeleteManyDisbursableDialog(i)
        is SendDeleteManyDisbursablesIntent -> sendDeleteManyDisbursable(i)
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
            val disbursement = service.disbursements.create(i.params.toValidatedDisbursableParams(i.disbursable.uid)).await()
            emit(state.copy(emphasis = Success("${disbursement.amount.toFormattedString()} has been successfully disbursed to ${i.disbursable.name} disbursable. Loading the remaining disbursables, please wait. . .")))
            emit(state.copy(table = disbursablesTable(service.all(DisbursableFilter(state.context?.uid)).await())))
        }.catch {
            emit(state.copy(emphasis = Failure(it) {
                onGoBack { post(ShowDisbursementForm(i.disbursable, i.params)) }
                onRetry { post(i) }
            }))
        }.collect {
            ui.update { it }
        }
    }

    private fun showDeleteOneDisbursable(i: ShowDeleteOneDisbursableDialog) {
        val confirm = confirmDialog("Delete ${i.disbursable.name}", "Are you sure you want to delete ${i.disbursable.name}?") {
            onCancel { ui.removeEmphasis() }
            onConfirm { post(SendDeleteOneDisbursableIntent(i.disbursable)) }
        }
        ui.update { copy(emphasis = Dialog(confirm)) }
    }

    private fun CoroutineScope.sendDeleteOneDisbursable(i: SendDeleteOneDisbursableIntent) = launch {
        val state = ui.value
        flow {
            emit(state.copy(emphasis = Loading("Deleting ${i.disbursable.name} disbursable, please wait . . .")))
            service.delete(i.disbursable.uid).await()
            emit(state.copy(emphasis = Success("Investment ${i.disbursable.name} deleted. Loading the remaining disbursables, please wait. . .")))
            emit(state.copy(table = disbursablesTable(service.all(DisbursableFilter(state.context?.uid)).await())))
        }.catch {
            emit(state.copy(emphasis = Failure(it) {
                onGoBack { ui.removeEmphasis() }
                onRetry { post(i) }
            }))
        }.collect {
            ui.update { it }
        }
    }

    private fun showDeleteManyDisbursableDialog(i: ShowDeleteManyDisbursablesDialog) {
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

    private fun CoroutineScope.sendDeleteManyDisbursable(i: SendDeleteManyDisbursablesIntent) = launch {
        val state = ui.value
        flow {
            emit(state.copy(emphasis = Loading("Deleting ${i.disbursables.size} disbursables, please wait. . .")))
            service.delete(*i.disbursables.map { it.uid }.toTypedArray()).await()
            emit(state.copy(emphasis = Success("${i.disbursables.size} Items deleted")))
            emit(state.copy(table = disbursablesTable(service.all(DisbursableFilter(state.context?.uid)).await())))
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
        val state = ui.value
        flow {
            emit(state.copy(emphasis = Loading("Loading context, please wait. . .")))
            val context = i.businessId?.let { api.businesses.load(it).await() }
            emit(state.copy(emphasis = Loading("We are almost done"), context = context))
            val table = disbursablesTable(service.all(DisbursableFilter(i.businessId)).await())
            emit(state.copy(context = context, table = table))
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