package bitframe.client.signin

import bitframe.client.components.TextInput
import bitframe.core.signin.RawSignInCredentials
import bitframe.core.signin.SignInCredentials
import kotlinx.css.*
import kotlinx.extensions.text
import kotlinx.html.InputType
import presenters.feedbacks.FormFeedback
import react.RBuilder
import reakt.ContainedButton
import reakt.Form
import reakt.Grid
import reakt.centerContent
import styled.css
import styled.styledDiv
import styled.styledH1

fun RBuilder.SignInForm(
    state: SignInState.Form,
    onLoginButtonPressed: (RawSignInCredentials) -> Unit
) = Grid {
    css {
        centerContent()
        height = 100.pct
    }
    val fields = state.fields
    Form {
        css { centerContent() }
        styledH1 { +fields.title }

        val status = state.status
        styledDiv {
            css {
                padding(vertical = 0.5.em)
                margin(vertical = 0.3.em)
                borderStyle = BorderStyle.solid
                borderWidth = 2.px
                borderColor = when (status) {
                    is FormFeedback.Loading -> Color.blue
                    is FormFeedback.Failure -> Color.red
                    is FormFeedback.Success -> Color.green
                    null -> Color.transparent
                }
            }
            +(status?.message ?: "")
        }
        TextInput(name = "email", fields.email)
        TextInput(name = "password", fields.password, type = InputType.password)
        if (status == null) ContainedButton(name = fields.submit.text)
    } onSubmit {
        val email by text()
        val password by text()
        onLoginButtonPressed(SignInCredentials(email, password))
    }
}