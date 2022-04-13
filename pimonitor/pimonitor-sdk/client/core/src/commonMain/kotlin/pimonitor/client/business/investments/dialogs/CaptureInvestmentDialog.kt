package pimonitor.client.business.investments.dialogs

import pimonitor.client.investments.fields.InvestmentFields
import pimonitor.client.business.investments.forms.CaptureInvestmentForm
import pimonitor.client.business.investments.params.InvestmentsRawParams
import presenters.forms.FormActionsBuildingBlock
import presenters.modal.FormDialog

class CaptureInvestmentDialog(
    monitoredName: String,
    block: FormActionsBuildingBlock<InvestmentsRawParams>
) : FormDialog<InvestmentFields, InvestmentsRawParams>(CaptureInvestmentForm(monitoredName, block))