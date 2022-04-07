package pimonitor.client.business.investments.dialogs

import pimonitor.client.business.investments.fields.CaptureInvestmentFields
import pimonitor.client.business.investments.forms.CaptureInvestmentForm
import pimonitor.client.business.investments.params.CreateInvestmentsRawFormParams
import presenters.forms.FormActionsBuildingBlock
import presenters.modal.FormDialog

class CaptureInvestmentDialog(
    monitoredName: String,
    block: FormActionsBuildingBlock<CreateInvestmentsRawFormParams>
) : FormDialog<CaptureInvestmentFields, CreateInvestmentsRawFormParams>(CaptureInvestmentForm(monitoredName, block))