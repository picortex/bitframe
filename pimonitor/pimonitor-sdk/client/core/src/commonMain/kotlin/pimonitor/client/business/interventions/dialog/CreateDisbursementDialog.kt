@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor.client.business.interventions.dialog

import pimonitor.client.business.utils.disbursements.CreateDisbursementForm
import presenters.forms.FormActionsBuildingBlock
import presenters.modal.FormDialog
import kotlin.js.JsExport
import pimonitor.client.business.utils.disbursements.CreateDisbursementFields as Fields
import pimonitor.core.business.investments.params.CreateInvestmentDisbursementRawParams as Params

class CreateDisbursementDialog(
    intervention: String,
    block: FormActionsBuildingBlock<Params>
) : FormDialog<Fields, Params>(CreateDisbursementForm(intervention, block))