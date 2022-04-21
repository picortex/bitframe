package pimonitor.client.utils.disbursable.disbursements

import bitframe.client.UIScopeConfig
import bitframe.core.Identified
import kash.Money
import kotlinx.collections.interoperable.List
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import later.await
import pimonitor.client.utils.disbursables.DisbursableService
import pimonitor.client.utils.disbursable.disbursements.DisbursementsIntent.*
import pimonitor.client.utils.date.toDefaultFormat
import pimonitor.client.utils.disbursable.disbursements.forms.CreateDisbursementForm
import pimonitor.client.utils.disbursable.disbursements.forms.UpdateDisbursementForm
import pimonitor.client.utils.live.removeEmphasis
import pimonitor.client.utils.live.update
import pimonitor.client.utils.money.toDefaultFormat
import pimonitor.core.utils.disbursables.Disbursable
import pimonitor.core.utils.disbursables.disbursements.Disbursement
import pimonitor.core.utils.disbursables.disbursements.params.toValidatedDisbursableParams
import presenters.cases.CentralState
import presenters.cases.Emphasis
import presenters.cases.Emphasis.Companion.Dialog
import presenters.cases.Emphasis.Companion.Failure
import presenters.cases.Emphasis.Companion.Loading
import presenters.cases.Emphasis.Companion.Success
import presenters.modal.confirmDialog
import presenters.numerics.Percentage
import presenters.table.builders.tableOf
import viewmodel.ViewModel

class DisbursementsViewModel(
    private val config: UIScopeConfig<DisbursableService<*, *>>
) : ViewModel<DisbursementsIntent, CentralState<Disbursable, Disbursement>>(DEFAULT_LOADING_STATE) {
    private val service get() = config.service

    companion object {
        private val DEFAULT_LOADING_STATE = CentralState<Disbursable, Disbursement>("Loading disbursements, please wait . . .")
    }

    private val disbursable: Disbursable
        get() = ui.value.context ?: throw IllegalStateException(
            "Disbursable context has not yet been set. Make sure you have called LoadDisbursements Intents"
        )

    override fun CoroutineScope.execute(i: DisbursementsIntent): Any = when (i) {
        is LoadDisbursements -> loadDisbursements(i)
        is ShowCreateDisbursementForm -> showCreateDisbursementForm(i)
        is SendCreateDisbursementForm -> sendCreateDisbursementForm(i)
        is ShowEditDisbursementForm -> showEditDisbursementForm(i)
        is SendEditDisbursementForm -> sendEditDisbursementForm(i)
        is ShowDeleteSingleConfirmationDialog -> showDeleteSingleConfirmationDialog(i)
        is Delete -> delete(i)
        is ShowDeleteMultipleConfirmationDialog -> showDeleteMultipleConfirmationDialog(i)
        is DeleteAll -> deleteAll(i)
    }

    private fun showCreateDisbursementForm(i: ShowCreateDisbursementForm) {
        val form = CreateDisbursementForm(i.disbursable, i.params) {
            onCancel { ui.removeEmphasis() }
            onSubmit { post(SendCreateDisbursementForm(i.disbursable, it)) }
        }
        ui.update { copy(emphasis = Dialog(form)) }
    }

    private fun CoroutineScope.sendCreateDisbursementForm(i: SendCreateDisbursementForm) = launch {
        val state = ui.value
        flow {
            emit(state.copy(emphasis = Loading("Creating a disbursement, please wait . . .")))
            val disbursement = service.createDisbursement(i.params.toValidatedDisbursableParams(i.disbursable.uid)).await()
            emit(state.copy(emphasis = Success("${disbursement.amount.toDefaultFormat()} has successfully been disbursed")))
            val context = service.load(disbursable.uid).await()
            emit(state.copy(table = disbursementTable(context.disbursements)))
        }.catch {
            emit(state.copy(emphasis = Failure(it) {
                onGoBack { post(ShowCreateDisbursementForm(i.disbursable, i.params)) }
                onRetry { post(i) }
            }))
        }.collect {
            ui.update { it }
        }
    }

    private fun showEditDisbursementForm(i: ShowEditDisbursementForm) {
        val form = UpdateDisbursementForm(i.disbursement, i.params) {
            onCancel { ui.removeEmphasis() }
            onSubmit { post(SendEditDisbursementForm(i.disbursement, it)) }
        }
        ui.update { copy(emphasis = Dialog(form)) }
    }

    private fun CoroutineScope.sendEditDisbursementForm(i: SendEditDisbursementForm) = launch {
        val state = ui.value
        flow {
            emit(state.copy(emphasis = Loading("Updating disbursement, please wait . . .")))
            val params = Identified(i.disbursement.uid, i.params.toValidatedDisbursableParams(disbursable.uid))
            val disbursement = service.updateDisbursement(params).await()
            emit(state.copy(emphasis = Success("${disbursement.amount.toDefaultFormat()} disbursement has been updated")))
            val context = service.load(disbursable.uid).await()
            emit(state.copy(table = disbursementTable(context.disbursements)))
        }.catch {
            emit(state.copy(emphasis = Failure(it) {
                onCancel { post(ShowEditDisbursementForm(i.disbursement, i.params)) }
                onRetry { post(i) }
            }))
        }.collect {
            ui.update { it }
        }
    }

    private fun showDeleteSingleConfirmationDialog(i: ShowDeleteSingleConfirmationDialog) {
        val confirm = confirmDialog(
            heading = "Delete Disbursement",
            details = "Are you sure you want to delete a ${i.disbursement.amount.toDefaultFormat()} disbursement?",
        ) {
            onCancel { ui.removeEmphasis() }
            onConfirm { post(Delete(i.disbursement)) }
        }
        ui.update { copy(emphasis = Dialog(confirm)) }
    }

    private fun CoroutineScope.delete(i: Delete) = launch {
        val state = ui.value
        flow {
            emit(state.copy(emphasis = Loading("Deleting ${i.disbursement.amount.toDefaultFormat()} from the list of disbursements?")))
            val params = Identified(
                uid = disbursable.uid,
                body = arrayOf(i.disbursement.uid)
            )
            val disbursement = service.deleteDisbursements(params).await()
            emit(state.copy(emphasis = Success("${disbursement.first().amount.toDefaultFormat()} disbursement has been deleted successfully")))
            val context = service.load(disbursable.uid).await()
            emit(state.copy(table = disbursementTable(context.disbursements)))
        }.catch {
            emit(state.copy(emphasis = Failure(it) {
                onGoBack { ui.removeEmphasis() }
                onRetry { post(i) }
            }))
        }.collect {
            ui.update { it }
        }
    }

    private fun showDeleteMultipleConfirmationDialog(i: ShowDeleteMultipleConfirmationDialog) {
        val confirm = confirmDialog(
            heading = "Delete Disbursements",
            details = "Are you sure you want to delete ${i.data.size} disbursements",
        ) {
            onCancel { ui.removeEmphasis() }
            onConfirm { post(DeleteAll(i.data)) }
        }
        ui.update { copy(emphasis = Dialog(confirm)) }
    }

    private fun CoroutineScope.deleteAll(i: DeleteAll) = launch {
        val state = ui.value
        flow {
            emit(state.copy(emphasis = Loading("Deleting ${i.data.size} disbursements")))
            val params = Identified(
                uid = disbursable.uid,
                body = i.data.map { it.data.uid }.toTypedArray()
            )
            service.deleteDisbursements(params).await()
            emit(state.copy(emphasis = Success("${i.data.size} disbursements have been deleted successfully")))
            val context = service.load(disbursable.uid).await()
            emit(state.copy(table = disbursementTable(context.disbursements)))
        }.catch {
            emit(state.copy(emphasis = Failure(it) {
                onGoBack { ui.removeEmphasis() }
                onRetry { post(i) }
            }))
        }.collect {
            ui.update { it }
        }
    }

    private fun CoroutineScope.loadDisbursements(i: LoadDisbursements) = launch {
        val state = ui.value
        flow {
            emit(DEFAULT_LOADING_STATE)
            val context = service.load(i.disbursableId).await()
            emit(state.copy(context = context, table = disbursementTable(context.disbursements)))
        }.catch {
            emit(state.copy(emphasis = Failure(it) {
                onRetry { post(i) }
            }))
        }.collect {
            ui.update { it }
        }
    }

    private fun Money.percentageOf(denominator: Money) = Percentage.fromRatio(amount.toDouble() / denominator.amount)

    private fun disbursementTable(data: List<Disbursement>) = tableOf(data.filter { !it.deleted }) {
        emptyMessage = "No Disbursements found"
        emptyDetails = "You haven't issued any disbursements"
        emptyAction("Issue a disbursement") { post(ShowCreateDisbursementForm(disbursable, null)) }

        primaryAction("Add a disbursement") { post(ShowCreateDisbursementForm(disbursable, null)) }
        primaryAction("Refresh") { post(LoadDisbursements(disbursable.uid)) }
        singleAction("Edit") { post(ShowEditDisbursementForm(it.data, null)) }
        singleAction("Delete") { post(ShowDeleteSingleConfirmationDialog(it.data)) }
        multiAction("Delete All") { post(ShowDeleteMultipleConfirmationDialog(it)) }

        selectable()
        column("Amount") { it.data.amount.toDefaultFormat() }
        column("percentage") { "${it.data.amount.percentageOf(disbursable.amount).asInt}%" }
        column("Disburse Date") { it.data.date.toDefaultFormat() }
        column("Disbursed By") { it.data.by.name }
        column("Recorded on") { it.data.on.toDefaultFormat() }
        actions {
            action("Edit") { post(ShowEditDisbursementForm(it.data, null)) }
            action("Delete") { post(ShowDeleteSingleConfirmationDialog(it.data)) }
        }
    }
}
