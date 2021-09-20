package pimonitor.authentication.signup

import kotlinx.css.em
import kotlinx.css.padding
import kotlinx.css.pct
import kotlinx.extensions.onDesktop
import kotlinx.extensions.onMobile
import kotlinx.extensions.text
import kotlinx.html.InputType
import pimonitor.Monitor
import pimonitor.MonitorParams
import pimonitor.presenters.TextInputField
import react.RBuilder
import reakt.*
import styled.css
import styled.styledH2
import theme.clazz

private fun RBuilder.TextInput(
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

fun RBuilder.NameEmailForm(
    params: NameEmailFormParams,
    onNext: (MonitorParams) -> Unit,
) = FlexBox {
    css {
        centerContent()
        onMobile { padding(horizontal = 1.em) }
        onDesktop { padding(horizontal = 20.pct) }
    }

    Form { theme ->
        styledH2 {
            css { +theme.text.h4.clazz }
            +params.title
        }

        TextInput("name", params.name)

        TextInput("email", params.email, InputType.email)

        Grid(cols = "1fr 1fr") {
            OutlinedButton("Cancel", onClick = params.prevButton.handler)
            ContainedButton("Next")
        }
    } onSubmit {
        val name by text()
        val email by text()
        onNext(MonitorParams(name, email))
    }
}