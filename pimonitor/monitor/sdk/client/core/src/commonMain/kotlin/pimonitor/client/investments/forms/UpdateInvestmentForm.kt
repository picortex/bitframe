package pimonitor.client.investments.forms

import pimonitor.core.businesses.models.MonitoredBusinessSummary
import pimonitor.core.investments.InvestmentSummary
import presenters.forms.Form
import presenters.forms.FormActionsBuildingBlock
import pimonitor.client.investments.fields.InvestmentFields as Fields
import pimonitor.core.investments.params.InvestmentRawParams as Params

fun UpdateInvestmentForm(
    businesses: List<MonitoredBusinessSummary>,
    investment: InvestmentSummary,
    params: Params? = null,
    block: FormActionsBuildingBlock<Params>,
) = Form(
    heading = "Update Investment",
    details = "Update ${investment.name} investment",
    fields = Fields(businesses, null, params, investment),
    block
)