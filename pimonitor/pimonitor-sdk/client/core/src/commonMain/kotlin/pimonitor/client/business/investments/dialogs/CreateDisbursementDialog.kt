@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor.client.business.investments.dialogs

import pimonitor.client.business.utils.disbursements.CreateDisbursementFields
import pimonitor.core.business.investments.params.CreateInvestmentDisbursementRawParams
import presenters.forms.FormActionsBuildingBlock
import presenters.modal.FormDialog
import kotlin.js.JsExport

class CreateDisbursementDialog(
    investmentName: String,
    block: FormActionsBuildingBlock<CreateInvestmentDisbursementRawParams>
) : FormDialog<CreateDisbursementFields, CreateInvestmentDisbursementRawParams>(
    heading = "Issue a Disbursement",
    details = "Issue a new disbursement for the $investmentName investment",
    fields = CreateDisbursementFields(),
    block
)