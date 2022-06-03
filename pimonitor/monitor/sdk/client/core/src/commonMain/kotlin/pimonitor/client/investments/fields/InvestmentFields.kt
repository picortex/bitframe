@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor.client.investments.fields

import kotlinx.collections.interoperable.toInteroperableList
import pimonitor.core.businesses.models.MonitoredBusinessSummary
import pimonitor.core.investments.InvestmentSummary
import pimonitor.core.investments.InvestmentType
import presenters.fields.*
import presenters.fields.DropDownInputField.Option
import kotlin.js.JsExport
import pimonitor.core.investments.params.InvestmentRawParams as Params

class InvestmentFields(
    businesses: List<MonitoredBusinessSummary>,
    business: MonitoredBusinessSummary?,
    params: Params?,
    investment: InvestmentSummary?
) {
    val name = TextInputField(
        name = Params::name,
        label = "Investment Name",
        hint = "Working Capital",
        value = params?.name ?: investment?.name
    )
    val businessId = DropDownInputField(
        name = Params::businessId,
        label = "Investments for",
        options = businesses.map { biz ->
            Option(label = biz.name, value = biz.uid, selected = biz.uid == business?.uid || biz.uid == investment?.businessId)
        }.toInteroperableList()
    )
    val type = DropDownInputField(
        name = Params::type,
        label = "Investment Type",
        options = InvestmentType.values().map {
            Option(label = it.name, selected = it.name == params?.type || it.name == investment?.type)
        }.toInteroperableList(),
    )
    val source = TextInputField(
        name = Params::source,
        label = "Investment Source",
        hint = "John Doe",
        value = params?.source ?: investment?.source
    )
    val amount = MoneyInputField(
        name = Params::amount,
        label = "Investment Amount",
        hint = "8,000",
        value = params?.amount ?: investment?.amount?.toInputValue()
    )
    val date: DateInputField = DateInputField(
        name = Params::date,
        label = "Investment Date",
        value = params?.date ?: investment?.date?.toIsoFormat()
    )
    val details: TextInputField = TextInputField(
        name = Params::details,
        label = "Investment Details",
        value = params?.details ?: investment?.details
    )
}