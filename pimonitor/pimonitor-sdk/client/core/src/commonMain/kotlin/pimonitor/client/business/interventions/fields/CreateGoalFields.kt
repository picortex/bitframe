@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor.client.business.interventions.fields

import kotlinx.collections.interoperable.listOf
import presenters.fields.DateInputField
import presenters.fields.DropDownInputField
import presenters.fields.DropDownInputField.Option
import presenters.fields.TextInputField
import kotlin.js.JsExport
import pimonitor.client.business.interventions.params.CreateGoalRawFormParams as Params

data class CreateGoalFields(
    val name: TextInputField = TextInputField(
        name = Params::name,
        label = "Name of goal"
    ),
    val item: DropDownInputField = DropDownInputField(
        name = Params::item,
        label = "Track Item",
        options = listOf(
            Option("Team Size")
        )
    ),
    val current: TextInputField = TextInputField(
        name = Params::current,
        label = "Current Value"
    ),
    val target: TextInputField = TextInputField(
        name = Params::target,
        label = "Target Value"
    ),
    val deadline: DateInputField = DateInputField(
        name = Params::deadline,
        label = "Deadline"
    )
)