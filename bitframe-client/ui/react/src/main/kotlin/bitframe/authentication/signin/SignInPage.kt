package bitframe.authentication.signin

import bitframe.authentication.signin.exports.SignInScope
import kotlinx.css.*
import kotlinx.html.js.onClickFunction
import react.Props
import react.RBuilder
import react.fc
import react.router.dom.withRouter
import react.useEffectOnce
import reakt.*
import styled.*
import useViewModelState

private external interface SignInPageProps : Props {
    var scope: SignInScope
    var version: String
}

private val SignInPage = fc<SignInPageProps> { props ->
    val scope = props.scope
    val viewModel = scope.viewModel
    val useSignInEvent = scope.useSignInEvent

    useSignInEvent {
        props.history.push("/panel")
    }

    styledDiv {
        css {
            position = Position.relative
            height = 100.vh
        }

        when (val ui = useViewModelState(viewModel)) {
            is SignInState.Form -> SignInForm(
                state = ui,
                onLoginButtonPressed = { viewModel.post(SignInIntent.Submit(it)) }
            )
            is SignInState.Conundrum -> Grid {
                css {
                    centerContent()
                }

                styledH2 {
                    +"Select Your Space"
                }

                ui.spaces.forEach { space ->
                    styledDiv {
                        css { cursor = Cursor.pointer }
                        +space.name
                        attrs.onClickFunction = {
                            scope.resolve(space)
                        }
                    }
                }
            }
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
) = child(withRouter(SignInPage)) {
    attrs.scope = SignInScope(service)
    attrs.version = version
}
