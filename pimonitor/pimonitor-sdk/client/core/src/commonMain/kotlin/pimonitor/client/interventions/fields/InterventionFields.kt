@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor.client.interventions.fields

import kotlinx.collections.interoperable.toInteroperableList
import pimonitor.core.businesses.models.MonitoredBusinessSummary
import pimonitor.core.interventions.InterventionSummary
import pimonitor.core.investments.params.InvestmentRawParams
import presenters.fields.*
import kotlin.js.JsExport
import pimonitor.core.interventions.params.InterventionRawParams as Params

class InterventionFields(
    businesses: List<MonitoredBusinessSummary>,
    business: MonitoredBusinessSummary?,
    params: Params?,
    investment: InterventionSummary?
) {
    val name = TextInputField(
        name = Params::name,
        label = "Intervention Name",
        hint = "Concur the world",
        value = params?.name ?: investment?.name
    )
    val businessId = DropDownInputField(
        name = InvestmentRawParams::businessId,
        label = "Investments for",
        options = businesses.map { biz ->
            DropDownInputField.Option(label = biz.name, value = biz.uid, selected = biz.uid == business?.uid || biz.uid == investment?.businessId)
        }.toInteroperableList()
    )
    val interventionDate = TextInputField(
        name = Params::date,
        label = "Intervention Date",
        value = params?.date ?: investment?.date?.toIsoFormat()
    )
    val interventionDeadline = TextInputField(
        name = Params::deadline,
        label = "Intervention Deadline",
        value = params?.deadline ?: investment?.deadline?.toIsoFormat()
    )
    val amount = NumberInputField(
        name = Params::amount,
        label = "Amount",
        value = params?.amount ?: investment?.amount?.toInputValue()
    )
    val recommendations = TextAreaField(
        name = Params::recommendations,
        value = params?.recommendations ?: investment?.recommendations
    )
}