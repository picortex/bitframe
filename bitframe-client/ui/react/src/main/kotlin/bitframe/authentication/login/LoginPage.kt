package bitframe.authentication.login

import bitframe.authentication.LoginService
import bitframe.authentication.LoginViewModel
import bitframe.authentication.LoginViewModel.Intent
import bitframe.authentication.LoginViewModel.State
import bitframe.authentication.login.LoginPage.Props
import kotlinx.css.*
import react.RBuilder
import react.RProps
import react.dom.span
import reakt.ErrorBox
import reakt.LoadingBox
import reakt.SuccessBox
import styled.css
import styled.styledDiv
import styled.styledSpan
import viewmodel.VComponent

@JsExport
class LoginPage private constructor(p: Props) : VComponent<Props, Intent, State, LoginViewModel>(p) {
    class Props(
        val service: LoginService,
        val version: String
    ) : RProps

    override val viewModel by lazy { LoginViewModel(props.service) }

    private fun RBuilder.ShowConundrum() = styledDiv {
        +"Yeeeiy connundrum"
    }

    override fun RBuilder.render(ui: State) = styledDiv {
        css { position = Position.relative }
        when (ui) {
            is State.Form -> LoginForm(
                cred = ui.credentials,
                onLoginButtonPressed = { viewModel.post(Intent.Login(it)) }
            )
            is State.Conundrum -> ShowConundrum()
            is State.Loading -> LoadingBox(ui.message)
            is State.Error -> ErrorBox(ui.throwable)
            is State.Success -> SuccessBox(ui.message)
        }

        styledSpan {
            css {
                position = Position.absolute
                bottom = 0.px
                left = 0.px
            }
            +"v${props.version}"
        }
    }
}

fun RBuilder.LoginPage(service: LoginService, version: String) = child(LoginPage::class.js, Props(service, version)) {}
