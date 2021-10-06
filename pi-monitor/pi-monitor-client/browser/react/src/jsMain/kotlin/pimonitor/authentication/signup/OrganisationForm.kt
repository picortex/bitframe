package pimonitor.authentication.signup

import bitframe.components.TextInput
import kotlinx.css.em
import kotlinx.css.padding
import kotlinx.css.pct
import kotlinx.extensions.onDesktop
import kotlinx.extensions.onMobile
import kotlinx.extensions.text
import kotlinx.html.InputType
import react.RBuilder
import reakt.*
import styled.css
import styled.styledH2
import theme.clazz

internal fun RBuilder.OrganisationForm(
    fields: OrganisationFormFields,
    onNext: (MonitorBusinessParams) -> Unit,
) = FlexBox {
    css {
        centerContent()
        onMobile { padding(horizontal = 1.em) }
        onDesktop { padding(horizontal = 20.pct) }
    }

    Form { theme ->
        styledH2 {
            css { +theme.text.h4.clazz }
            +fields.title
        }

        TextInput("name", fields.name)

        TextInput("email", fields.email, InputType.email)

        Grid(cols = "1fr 1fr") {
            OutlinedButton("Cancel", onClick = fields.prevButton.handler)
            ContainedButton("Next")
        }
    } onSubmit {
        val name by text()
        val email by text()
        onNext(MonitorBusinessParams(name, email))
    }
}