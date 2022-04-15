package pimonitor.client.utils.disbursements

import bitframe.client.UIScopeConfig
import bitframe.core.Identified
import kash.Money
import kotlinx.collections.interoperable.List
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import later.await
import pimonitor.client.utils.DisbursableService
import pimonitor.client.utils.disbursements.DisbursementsIntent.*
import pimonitor.client.utils.date.toDefaultFormat
import pimonitor.client.utils.live.removeEmphasis
import pimonitor.client.utils.live.update
import pimonitor.client.utils.money.toDefaultFormat
import pimonitor.core.utils.disbursements.Disbursable
import pimonitor.core.utils.disbursements.Disbursement
import presenters.cases.CentralState
import presenters.cases.Emphasis
import presenters.cases.Emphasis.Companion.Failure
import presenters.cases.Emphasis.Companion.Loading
import presenters.numerics.Percentage
import presenters.table.builders.tableOf
import viewmodel.ViewModel

class DisbursementsViewModel(
    private val config: UIScopeConfig<DisbursableService>
) : ViewModel<DisbursementsIntent, CentralState<Disbursement>>(DEFAULT_LOADING_STATE) {
    private val service get() = config.service

    companion object {
        private val DEFAULT_LOADING_STATE = CentralState<Disbursement>("Loading disbursements, please wait . . .")
    }

    private lateinit var disbursable: Disbursable
    override fun CoroutineScope.execute(i: DisbursementsIntent): Any = when (i) {
        is LoadDisbursements -> loadDisbursements(i)
        is ShowCreateDisbursementForm -> TODO()
        is SendCreateDisbursementForm -> TODO()
        is ShowEditDisbursementForm -> TODO()
        is SendEditDisbursementForm -> TODO()
        is ShowDeleteSingleConfirmationDialog -> TODO()
        is Delete -> delete(i)
        is ShowDeleteMultipleConfirmationDialog -> showDeleteMultipleConfirmationDialog(i)
        is DeleteAll -> deleteAll(i)
    }

    private fun CoroutineScope.showDeleteMultipleConfirmationDialog(i: ShowDeleteMultipleConfirmationDialog) = launch {
        val state = ui.value
    }

    private fun CoroutineScope.delete(i: Delete) = launch {
        val state = ui.value
        flow {
            emit(state.copy(emphasis = Loading("Deleting ${i.disbursement.amount.toDefaultFormat()} from the list of disbursements")))
            val params = Identified(
                uid = disbursable.uid,
                body = arrayOf(i.disbursement.uid)
            )
            service.deleteDisbursements(params).await()
            disbursable = service.load(disbursable.uid).await()
            emit(state.copy(table = disbursementTable(disbursable.disbursements)))
        }.catch {
            emit(state.copy(emphasis = Failure(it) {
                onGoBack { ui.removeEmphasis() }
                onRetry { post(i) }
            }))
        }.collect {
            ui.update { it }
        }
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
            disbursable = service.load(disbursable.uid).await()
            emit(state.copy(table = disbursementTable(disbursable.disbursements)))
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
            disbursable = service.load(i.disbursableId).await()
            emit(state.copy(table = disbursementTable(disbursable.disbursements)))
        }.catch {
            emit(state.copy(emphasis = Emphasis.Failure(it) {
                onRetry { post(i) }
            }))
        }.collect {
            ui.update { it }
        }
    }

    private fun Money.percentageOf(denominator: Money) = Percentage.fromRatio(amount.toDouble() / denominator.amount)

    private fun disbursementTable(data: List<Disbursement>) = tableOf(data) {
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