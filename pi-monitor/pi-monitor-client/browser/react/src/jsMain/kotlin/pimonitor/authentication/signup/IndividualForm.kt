package pimonitor.authentication.signup

import bitframe.components.*
import kotlinx.css.em
import kotlinx.css.padding
import kotlinx.css.pct
import kotlinx.extensions.onDesktop
import kotlinx.extensions.onMobile
import kotlinx.extensions.text
import kotlinx.html.InputType
import pimonitor.MonitorPersonParams
import react.RBuilder
import reakt.*
import styled.css
import styled.styledH2
import theme.clazz

internal fun RBuilder.IndividualForm(
    fields: IndividualFormFields,
    onNext: (MonitorPersonParams) -> Unit,
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

        TextInput("password", fields.password, InputType.password)

        Grid(cols = "1fr 1fr") {
            OutlinedButton("Cancel", onClick = fields.prevButton.handler)
            ContainedButton("Next")
        }
    } onSubmit {
        val name by text()
        val email by text()
        val password by text()
        onNext(MonitorPersonParams(name, email, password))
    }
}