package bitframe.authentication.signin

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
    onLoginButtonPressed: (LoginCredentials) -> Unit
) = Grid {
    css {
        centerContent()
        height = 100.pct
    }

    Form {
        css { centerContent() }
        styledH1 { +fields.title }
        TextInput(name = "email", fields.email)
        TextInput(name = "pass", fields.password, type = InputType.password)
        ContainedButton(name = fields.title)
    } onSubmit {
        val email by text()
        val pass by text()
        onLoginButtonPressed(LoginCredentials(email, pass))
    }
}