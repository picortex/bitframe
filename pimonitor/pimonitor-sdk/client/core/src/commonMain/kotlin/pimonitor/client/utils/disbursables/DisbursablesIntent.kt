package pimonitor.client.utils.disbursables

import pimonitor.core.utils.disbursables.Disbursable
import pimonitor.core.utils.disbursables.disbursements.params.DisbursementRawParams
import presenters.table.Row

interface DisbursablesIntent {
    open class LoadAllDisbursables(val businessId: String?) : DisbursablesIntent

    open class ShowDisbursementForm(val disbursable: Disbursable, val params: DisbursementRawParams?) : DisbursablesIntent
    open class SendDisbursementForm(val disbursable: Disbursable, val params: DisbursementRawParams) : DisbursablesIntent

    open class ShowDeleteOneDisbursableDialog(val disbursable: Disbursable) : DisbursablesIntent
    open class SendDeleteOneDisbursableIntent(val disbursable: Disbursable) : DisbursablesIntent

    open class ShowDeleteManyDisbursablesDialog(val disbursables: Array<out Row<Disbursable>>) : DisbursablesIntent
    open class SendDeleteManyDisbursablesIntent(val disbursables: Array<out Disbursable>) : DisbursablesIntent
}