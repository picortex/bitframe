package bitframe.components

import presenters.fields.TextInputField
import kotlinx.html.InputType
import react.RBuilder
import reakt.TextInput

fun RBuilder.TextInput(
    name: String,
    from: TextInputField,
    type: InputType = InputType.text
) = TextInput(
    name = name,
    label = from.label,
    hint = from.hint,
    value = from.value,
    type = type
)