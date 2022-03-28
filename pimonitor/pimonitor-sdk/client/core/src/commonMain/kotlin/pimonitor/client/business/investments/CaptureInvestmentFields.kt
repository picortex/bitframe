package pimonitor.client.business.investments

import kotlinx.collections.interoperable.toInteroperableList
import pimonitor.core.business.investments.InvestmentType
import presenters.fields.DateInputField
import presenters.fields.DropDownInputField
import presenters.fields.DropDownInputField.Option
import presenters.fields.TextInputField
import pimonitor.core.business.investments.CaptureInvestmentsParams as Params

data class CaptureInvestmentFields(
    val name: TextInputField = TextInputField(
        name = Params::name,
        label = "Investment Name",
        hint = "Working Capital"
    ),
    val type: DropDownInputField = DropDownInputField(
        name = Params::type,
        label = "Investment Type",
        options = InvestmentType.values().map { Option(label = it.name) }.toInteroperableList()
    ),
    val source: TextInputField = TextInputField(
        name = Params::source,
        label = "Investment Source",
        hint = "John Doe"
    ),
    val amount: TextInputField = TextInputField(
        name = Params::amount,
        label = "Investment Amount",
        hint = "8,000"
    ),
    val date: DateInputField = DateInputField(
        name = Params::date,
        label = "Investment Date",
    ),
    val details: TextInputField = TextInputField(
        name = Params::details,
        label = "Investment Details"
    )
)