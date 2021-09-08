package pimonitor.authentication.signup

import contacts.Email
import kotlinx.css.label
import kotlinx.extensions.text
import kotlinx.html.InputType
import pimonitor.Monitor
import react.RBuilder
import reakt.ContainedButton
import reakt.Form
import reakt.Grid
import reakt.TextInput
import styled.css
import styled.styledH2
import theme.clazz

fun RBuilder.StageOne(
    business: Monitor.Business?,
    onNext: (Monitor.Business) -> Unit,
    onCancel: () -> Unit
) = Form { theme ->
    styledH2 {
        css { +theme.text.h4.clazz }
        +"Enter Business Info"
    }

    TextInput(
        name = "name",
        label = "Business Name",
        hint = "John Doe Inc.",
        value = business?.name
    )

    TextInput(
        name = "email",
        type = InputType.email,
        label = "Business Email",
        hint = "support@johndoeinc.com",
        value = business?.email?.toString()
    )

    Grid(cols = "1fr 1fr") {
        ContainedButton("Cancel", onClick = onCancel)
        ContainedButton("Next")
    }
} onSubmit {
    val name by text()
    val email by text()
    onNext(Monitor.Business(name, Email(email), null))
}