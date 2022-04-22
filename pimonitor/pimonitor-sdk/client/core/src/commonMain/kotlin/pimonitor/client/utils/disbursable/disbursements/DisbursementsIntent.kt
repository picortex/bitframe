package pimonitor.client.utils.disbursable.disbursements

import pimonitor.core.utils.disbursables.Disbursable
import pimonitor.core.utils.disbursables.disbursements.Disbursement
import pimonitor.core.utils.disbursables.disbursements.params.DisbursementRawParams
import presenters.table.Row

sealed class DisbursementsIntent {
    data class LoadDisbursements(val disbursableId: String) : DisbursementsIntent()

    data class ShowCreateDisbursementForm(val disbursable: Disbursable, val params: DisbursementRawParams?) : DisbursementsIntent()
    data class SendCreateDisbursementForm(val disbursable: Disbursable, val params: DisbursementRawParams) : DisbursementsIntent()

    data class ShowEditDisbursementForm(val disbursement: Disbursement, val params: DisbursementRawParams?) : DisbursementsIntent()
    data class SendEditDisbursementForm(val disbursement: Disbursement, val params: DisbursementRawParams) : DisbursementsIntent()

    data class ShowDeleteSingleConfirmationDialog(val disbursement: Disbursement) : DisbursementsIntent()
    data class ShowDeleteMultipleConfirmationDialog(val data: Array<Row<Disbursement>>) : DisbursementsIntent()

    data class Delete(val disbursement: Disbursement) : DisbursementsIntent()
    data class DeleteAll(val data: Array<Row<Disbursement>>) : DisbursementsIntent()
}