package pimonitor.authentication.signup

import kotlinx.css.minHeight
import kotlinx.css.vh
import pimonitor.PiMonitorService
import react.Props
import react.RBuilder
import react.fc
import react.router.dom.useHistory
import react.router.dom.withRouter
import reakt.ErrorBox
import reakt.Grid
import reakt.LoadingBox
import reakt.SuccessBox
import styled.css
import useViewModelState

private external class SignUpProps : Props {
    var scope: SignUpScope
}

private val SignUp = fc<SignUpProps> { props ->
    val state = useViewModelState(props.scope.viewModel)
    val history = useHistory()
    Grid {
        css { minHeight = 100.vh }

        when (state) {
            is SignUpState.Loading -> LoadingBox(state.message)
            is SignUpState.Form -> SignUpForm(props.scope, onCancel = { history.push("/") }, state)
            is SignUpState.Failure -> ErrorBox(state.cause)
            is SignUpState.Success -> SuccessBox(state.message)
        }
    }
}

fun RBuilder.SignUp(service: PiMonitorService) = child(withRouter(SignUp)) {
    attrs.scope = SignUpScope(service)
}