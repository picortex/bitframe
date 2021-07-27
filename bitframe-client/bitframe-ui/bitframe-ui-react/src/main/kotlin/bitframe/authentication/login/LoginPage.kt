package bitframe.authentication.login

import bitframe.authentication.LoginService
import bitframe.authentication.LoginViewModel
import bitframe.authentication.LoginViewModel.Intent
import bitframe.authentication.LoginViewModel.State
import bitframe.authentication.login.LoginPage.Props
import react.RBuilder
import react.RProps
import reakt.ErrorBox
import reakt.LoadingBox
import reakt.SuccessBox
import styled.styledDiv
import viewmodel.VComponent

@JsExport
class LoginPage private constructor(p: Props) : VComponent<Props, Intent, State, LoginViewModel>(p) {
    class Props(
        val service: LoginService
    ) : RProps

    override val viewModel by lazy { LoginViewModel(props.service) }

    override fun componentDidMount() {
        super.componentDidMount()
        viewModel.post(Intent.ShowForm(null))
    }

    private fun RBuilder.ShowConundrum() = styledDiv {
        +"Yeeeiy connundrum"
    }

    override fun RBuilder.render(ui: State) = when (ui) {
        is State.Form -> LoginForm(
            cred = ui.credentials,
            onLoginButtonPressed = { viewModel.post(Intent.Login(it)) }
        )
        is State.Conundrum -> ShowConundrum()
        is State.Loading -> LoadingBox(ui.message)
        is State.Error -> ErrorBox(ui.throwable)
        is State.Success -> SuccessBox(ui.message)
    }
}

fun RBuilder.LoginPage(service: LoginService) = child(LoginPage::class.js, Props(service)) {}
