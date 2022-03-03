package pimonitor.client.businesses.forms

import kotlinx.collections.interoperable.listOf
import presenters.fields.DateInputField
import presenters.fields.DropDownInputField
import presenters.fields.DropDownInputField.Option
import presenters.fields.TextInputField

data class CaptureInvestmentFields(
    val investmentType: DropDownInputField = DropDownInputField(
        name = "investmentType",
        label = "Investment Type",
        options = listOf(
            Option(label = "Loan"),
            Option(label = "Debt Equity"),
            Option(label = "Royalty")
        )
    ),
    val investmentSource: TextInputField = TextInputField(
        name = "investmentSource",
        label = "Investment Source",
        hint = "John Doe"
    ),
    val investmentAmount: TextInputField = TextInputField(
        name = "investmentAmount",
        label = "Investment Amount",
        hint = "8000"
    ),
    val investmentDate: DateInputField = DateInputField(
        name = "investmentDate",
        label = "Investment Date",
    ),
    val investmentDetails: TextInputField = TextInputField(
        name = "investmentDetails",
        label = "Investment Details"
    )
)