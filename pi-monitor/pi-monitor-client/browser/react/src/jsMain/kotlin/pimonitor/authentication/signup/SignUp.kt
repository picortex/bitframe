package pimonitor.authentication.signup

import kotlinx.css.minHeight
import kotlinx.css.vh
import pimonitor.authentication.SignUpService
import react.Props
import react.RBuilder
import react.fc
import react.router.dom.withRouter
import reakt.ErrorBox
import reakt.Grid
import reakt.LoadingBox
import reakt.SuccessBox
import styled.css
import useViewModelState

private external class SignUpProps : Props {
    var viewModel: SignUpViewModel
}

private val SignUp = fc<SignUpProps> { props ->
    val state = useViewModelState(props.viewModel)
    Grid {
        css { minHeight = 100.vh }

        when (state) {
            is SignUpState.Loading -> LoadingBox(state.message)
            is SignUpState.Form -> SignUpForm(props.viewModel, state)
            is SignUpState.Failure -> ErrorBox(state.cause)
            is SignUpState.Success -> SuccessBox(state.message)
        }
    }
}

fun RBuilder.SignUp(service: SignUpService) = child(withRouter(SignUp)) {
    attrs.viewModel = SignUpViewModel(service)
}