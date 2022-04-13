package pimonitor.client.investments.forms

import pimonitor.core.businesses.MonitoredBusinessBasicInfo
import pimonitor.core.businesses.models.MonitoredBusinessSummary
import presenters.forms.Form
import presenters.forms.FormActionsBuildingBlock
import pimonitor.client.investments.fields.InvestmentFields as Fields
import pimonitor.core.investments.params.InvestmentsRawParams as Params

fun CreateInvestmentForm(
    businesses: List<MonitoredBusinessSummary>,
    business: MonitoredBusinessBasicInfo?,
    params: Params? = null,
    block: FormActionsBuildingBlock<Params>
) = Form(
    heading = "Capture Investment",
    details = "Capture Investments for a business",
    fields = Fields(businesses, business, params, null),
    block
)