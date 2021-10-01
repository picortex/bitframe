package bitframe.authentication.signin.legacy

import bitframe.authentication.signin.SignInCredentials
import bitframe.authentication.signin.SignInFormFields
import bitframe.components.TextInput
import kotlinx.css.height
import kotlinx.css.pct
import kotlinx.extensions.text
import kotlinx.html.InputType
import react.RBuilder
import reakt.*
import styled.css
import styled.styledH1

fun RBuilder.SignInForm(
    fields: SignInFormFields,
    onLoginButtonPressed: (SignInCredentials) -> Unit
) = Grid {
    css {
        centerContent()
        height = 100.pct
    }

    Form {
        css { centerContent() }
        styledH1 { +fields.title }
        TextInput(name = "email", fields.email)
        TextInput(name = "password", fields.password, type = InputType.password)
        ContainedButton(name = fields.submit.text)
    } onSubmit {
        val email by text()
        val password by text()
        onLoginButtonPressed(SignInCredentials(email, password))
    }
}