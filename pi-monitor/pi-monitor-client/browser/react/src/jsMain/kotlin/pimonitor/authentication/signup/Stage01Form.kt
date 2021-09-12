package pimonitor.authentication.signup

import kotlinx.extensions.text
import kotlinx.html.InputType
import pimonitor.Monitor
import pimonitor.MonitorParams
import react.RBuilder
import reakt.*
import styled.css
import styled.styledDiv
import styled.styledH2
import theme.clazz

fun RBuilder.Stage01Form(
    business: MonitorParams?,
    onNext: (MonitorParams) -> Unit,
    onCancel: () -> Unit
) = Form { theme ->
    styledH2 {
        css { +theme.text.h4.clazz }
        +"Enter Business Info"
    }

    TextInput(
        name = "bName",
        label = "Business Name",
        hint = "John Doe Inc.",
        value = business?.name
    )

    TextInput(
        name = "bEmail",
        type = InputType.email,
        label = "Business Email",
        hint = "support@johndoeinc.com",
        value = business?.email
    )

    Grid(cols = "1fr 1fr") {
        OutlinedButton("Cancel", onClick = onCancel)
        ContainedButton("Next")
    }
} onSubmit {
    val bName by text()
    val bEmail by text()
    onNext(MonitorParams(bName, bEmail))
    reset()
}