package bitframe.authentication.signin.legacy

import bitframe.authentication.signin.SignInService
import bitframe.authentication.signin.exports.SignInScopeLegacy
import kotlinx.css.*
import react.Props
import react.RBuilder
import react.fc
import react.router.dom.withRouter
import react.useEffectOnce
import reakt.ErrorBox
import reakt.LoadingBox
import reakt.SuccessBox
import reakt.history
import styled.css
import styled.styledDiv
import styled.styledSpan
import useViewModelState

private external interface SignInPageProps : Props {
    var scope: SignInScopeLegacy
    var version: String
}

private fun RBuilder.ShowConundrum() = styledDiv {
    +"Yeeeiy connundrum"
}

private val SignInPage = fc<SignInPageProps> { props ->
    val viewModel = props.scope.viewModel
    val ui = useViewModelState(viewModel)
    useEffectOnce {
        viewModel.onUserLoggedIn { _, _ ->
            props.history.push("/panel")
        }
    }

    styledDiv {
        css {
            position = Position.relative
            height = 100.vh
        }
        when (ui) {
            is SignInState.Form -> SignInForm(
                fields = ui.fields,
                onLoginButtonPressed = { viewModel.post(SignInIntent.Submit(it)) }
            )
            is SignInState.Conundrum -> ShowConundrum()
            is SignInState.Loading -> LoadingBox(ui.message)
            is SignInState.Failure -> ErrorBox(ui.cause)
            is SignInState.Success -> SuccessBox(ui.message)
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

fun RBuilder.SignInPageLegacy(
    service: SignInService<*>,
    version: String
) = child(withRouter(SignInPage)) {
    attrs.scope = SignInScopeLegacy(service)
    attrs.version = version
}
