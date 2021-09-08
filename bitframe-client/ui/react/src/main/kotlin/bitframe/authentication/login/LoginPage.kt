package bitframe.authentication.login

import bitframe.authentication.SignInService
import bitframe.authentication.LoginViewModel
import bitframe.authentication.LoginViewModel.Intent
import bitframe.authentication.LoginViewModel.State
import kotlinx.css.*
import react.RBuilder
import react.Props
import react.router.dom.withRouter
import reakt.*
import styled.css
import styled.styledDiv
import styled.styledSpan
import viewmodel.VComponent

external interface LoginPageProps : Props {
    var service: SignInService
    var version: String
}

@JsExport
class LoginPage private constructor(p: LoginPageProps) : VComponent<LoginPageProps, Intent, State, LoginViewModel>(p) {
    override val viewModel by lazy { LoginViewModel(props.service) }

    override fun componentDidMount() {
        super.componentDidMount()
        viewModel.onUserLoggedIn { _, _ ->
            props.history.push("/panel")
        }
    }

    private fun RBuilder.ShowConundrum() = styledDiv {
        +"Yeeeiy connundrum"
    }

    override fun RBuilder.render(ui: State) = styledDiv {
        css {
            position = Position.relative
            height = 100.vh
        }
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

fun RBuilder.SignInPage(
    service: SignInService,
    version: String
) = child(withRouter(LoginPage::class)) {
    attrs.service = service
    attrs.version = version
}
