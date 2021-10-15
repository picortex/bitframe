package bitframe.authentication.signin

import bitframe.authentication.signin.exports.SignInScope
import kotlinx.css.*
import react.Props
import react.RBuilder
import react.fc
import react.router.dom.withRouter
import react.useEffectOnce
import reakt.*
import styled.css
import styled.styledDiv
import styled.styledSpan
import useViewModelState

private external interface SignInPageProps : Props {
    var scope: SignInScope
    var version: String
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

        SignInForm(
            state = ui,
            onLoginButtonPressed = { viewModel.post(SignInIntent.Submit(it)) }
        )

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
    service: SignInService<*>,
    version: String
) = child(withRouter(SignInPage)) {
    attrs.scope = SignInScope(service)
    attrs.version = version
}
