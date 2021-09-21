package bitframe.authentication.login

import bitframe.authentication.LoginCredentials
import kotlinx.css.height
import kotlinx.css.pct
import kotlinx.extensions.text
import kotlinx.html.InputType
import react.RBuilder
import reakt.*
import styled.css
import styled.styledH1

fun RBuilder.LoginForm(
    cred: LoginCredentials?,
    onLoginButtonPressed: (LoginCredentials) -> Unit
) = Grid {
    css {
        centerContent()
        height = 100.pct
    }

    Form {
        css { centerContent() }
        styledH1 {
            +"Secure Login"
        }
        TextInput(name = "username", label = "Username", value = cred?.alias)
        TextInput(name = "pass", label = "Password", type = InputType.password, value = cred?.password)
        ContainedButton(name = "Login")
    } onSubmit {
        val username by text()
        val pass by text()
        onLoginButtonPressed(LoginCredentials(username, pass))
    }
}