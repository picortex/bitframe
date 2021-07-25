package bitframe

import kotlinx.browser.window
import kotlinx.css.height
import kotlinx.css.pct
import kotlinx.extensions.text
import kotlinx.html.InputType
import react.RBuilder
import reakt.Form
import reakt.Grid
import reakt.TextInput
import reakt.centerContent
import styled.css
import styled.styledH1

fun RBuilder.Bitframe() = Grid {
    css {
        centerContent()
        height = 100.pct
    }

    Form {
        css { centerContent() }
        styledH1 {
            +"Secure Login"
        }
        TextInput(name = "username", label = "Username")
        TextInput(name = "pass", label = "Password", type = InputType.password)
    } onSubmit {
        val username by text()
        val pass by text()
        window.alert("Username: $username, Password: $pass")
    }
}