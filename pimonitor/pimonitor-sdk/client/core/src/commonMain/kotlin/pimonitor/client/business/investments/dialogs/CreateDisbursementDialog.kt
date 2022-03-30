@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor.client.business.investments.dialogs

import pimonitor.client.business.utils.disbursements.CreateDisbursementFields
import pimonitor.core.business.investments.params.CreateInvestmentDisbursementRawParams
import presenters.modal.Dialog
import presenters.modal.builders.FormDialogBuildingBlock
import kotlin.js.JsExport

class CreateDisbursementDialog(
    investmentName: String,
    block: FormDialogBuildingBlock<CreateInvestmentDisbursementRawParams>
) : Dialog.Form<CreateDisbursementFields, CreateInvestmentDisbursementRawParams>(
    heading = "Issue a Disbursement",
    details = "Issue a new disbursement for the $investmentName investment",
    fields = CreateDisbursementFields(),
    block
)