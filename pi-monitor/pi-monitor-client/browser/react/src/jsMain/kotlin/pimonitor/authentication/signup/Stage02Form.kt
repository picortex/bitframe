package pimonitor.authentication.signup

import kotlinx.extensions.text
import kotlinx.html.InputType
import pimonitor.Monitor
import pimonitor.MonitorParams
import react.RBuilder
import reakt.*
import styled.css
import styled.styledH2
import theme.clazz

fun RBuilder.Stage02Form(
    person: MonitorParams?,
    onNext: (MonitorParams) -> Unit,
    onCancel: () -> Unit
) = Form { theme ->
    styledH2 {
        css { +theme.text.h4.clazz }
        +"Enter Your Personal Information"
    }

    TextInput(
        name = "name",
        label = "Name",
        hint = "John Doe Inc.",
        value = person?.name
    )

    TextInput(
        name = "email",
        type = InputType.email,
        label = "Email",
        hint = "support@johndoeinc.com",
        value = person?.email
    )

    Grid(cols = "1fr 1fr") {
        OutlinedButton("Cancel", onClick = onCancel)
        ContainedButton("Next")
    }
} onSubmit {
    val name by text()
    val email by text()
    onNext(MonitorParams(name, email))
}