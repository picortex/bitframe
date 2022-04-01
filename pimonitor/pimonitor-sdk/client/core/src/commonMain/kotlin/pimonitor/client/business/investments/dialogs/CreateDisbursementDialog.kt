@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor.client.business.investments.dialogs

import pimonitor.client.business.utils.disbursements.CreateDisbursementForm
import presenters.forms.FormActionsBuildingBlock
import presenters.modal.FormDialog
import kotlin.js.JsExport
import pimonitor.client.business.utils.disbursements.CreateDisbursementFields as Fields
import pimonitor.client.business.utils.disbursements.CreateDisbursementRawFormParams as Params

class CreateDisbursementDialog(
    investment: String,
    block: FormActionsBuildingBlock<Params>
) : FormDialog<Fields, Params>(CreateDisbursementForm(investment, block))