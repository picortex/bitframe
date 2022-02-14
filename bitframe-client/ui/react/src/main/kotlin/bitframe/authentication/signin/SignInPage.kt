package bitframe.authentication.signin

import bitframe.client.BitframeReactAppScope
import kotlinx.css.*
import kotlinx.html.js.onClickFunction
import react.Props
import react.RBuilder
import react.fc
import react.router.useNavigate
import react.useEffectOnce
import reakt.Grid
import reakt.centerContent
import styled.css
import styled.styledDiv
import styled.styledH2
import styled.styledSpan
import useViewModelState

private external interface SignInPageProps : Props {
    var scope: SignInReactScope
    var version: String
}

private val SignInPage = fc<SignInPageProps> { props ->
    val scope = props.scope
    val viewModel = scope.viewModel
    val useSignInEvent = scope.useSignInEvent
    val navigate = useNavigate()

    useEffectOnce { scope.initForm() }

    useSignInEvent {
        navigate("/panel")
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
                            console.log("Clicked")
                            scope.resolveConundrum(space)
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
    scope: BitframeReactAppScope,
    version: String
) = SignInPage {
    attrs.scope = scope.signIn
    attrs.version = version
}
