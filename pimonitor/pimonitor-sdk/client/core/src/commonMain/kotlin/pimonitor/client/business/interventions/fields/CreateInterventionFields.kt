@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor.client.business.interventions.fields

import presenters.fields.NumberInputField
import presenters.fields.TextAreaField
import presenters.fields.TextInputField
import kotlin.js.JsExport
import pimonitor.client.business.interventions.params.CreateInterventionRawFormParams as Params

data class CreateInterventionFields(
    val name: TextInputField = TextInputField(
        name = Params::name,
        label = "Intervention Name",
        hint = "Concur the world"
    ),
    val interventionDate: TextInputField = TextInputField(
        name = Params::date,
        label = "Intervention Date"
    ),
    val interventionDeadline: TextInputField = TextInputField(
        name = Params::deadline,
        label = "Intervention Deadline"
    ),
    val amount: NumberInputField = NumberInputField(
        name = Params::amount,
        label = "Amount"
    ),
    val recommendations: TextAreaField = TextAreaField(
        name = Params::recommendations
    )
)