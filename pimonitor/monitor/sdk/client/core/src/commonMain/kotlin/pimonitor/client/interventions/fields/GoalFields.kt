@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor.client.interventions.fields

import kotlinx.collections.interoperable.listOf
import pimonitor.core.interventions.Goal
import presenters.fields.DateInputField
import presenters.fields.DropDownInputField
import presenters.fields.DropDownInputField.Option
import presenters.fields.NumberInputField
import presenters.fields.TextInputField
import kotlin.js.JsExport
import pimonitor.client.interventions.params.GoalRawParams as Params

class GoalFields(params: Params?, goal: Goal?) {
    val name = TextInputField(
        name = Params::name,
        label = "Name of goal",
        value = params?.name ?: goal?.name
    )
    val item = DropDownInputField(
        name = Params::item,
        label = "Track Item",
        options = listOf(
            Option("Team Size")
        )
    )
    val current = NumberInputField(
        name = Params::current,
        label = "Current Value",
        value = params?.current ?: goal?.currentValue?.toString()
    )
    val target = NumberInputField(
        name = Params::target,
        label = "Target Value",
        value = params?.target ?: goal?.targetValue?.toString()
    )
    val deadline = DateInputField(
        name = Params::deadline,
        label = "Deadline",
        value = params?.deadline
    )
}