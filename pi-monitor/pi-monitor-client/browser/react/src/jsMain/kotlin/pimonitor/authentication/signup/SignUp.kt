package pimonitor.authentication.signup

import kotlinx.css.height
import kotlinx.css.vh
import react.Props
import react.RBuilder
import react.fc
import react.router.dom.withRouter
import reakt.Grid
import reakt.ProgressBar
import reakt.centerContent
import styled.css
import styled.styledDiv
import useViewModelState

private external class SignUpProps : Props {
    var viewModel: SignUpViewModel
}

private val SignUp = fc<SignUpProps> { props ->
    val state = useViewModelState(props.viewModel)
    Grid {
        css {
            centerContent()
            height = 100.vh
        }

        when (state) {
            is SignUpViewModel.State.Form -> styledDiv {
                ProgressBar(state.progress * 100)
                +state.toString()
            }
        }
    }
}

fun RBuilder.SignUp(vm: SignUpViewModel) = child(withRouter(SignUp)) {
    attrs.viewModel = vm
}