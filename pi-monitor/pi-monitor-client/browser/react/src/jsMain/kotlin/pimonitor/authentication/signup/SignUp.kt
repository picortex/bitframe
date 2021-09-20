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
    val scope = props.scope
    val viewModel = scope.viewModel
    val state = useViewModelState(viewModel)
    val history = useHistory()
    Grid {
        css { minHeight = 100.vh }

        when (state) {
            is SignUpState.Loading -> LoadingBox(state.message)
            is SignUpState.Failure -> ErrorBox(state.cause)
            is SignUpState.Success -> SuccessBox(state.message)
            is SignUpState.NameEmailForm -> NameEmailForm(
                params = state.params,
                onNext = { viewModel.post(SignUpIntent.SubmitForm(it)) }
            )
            SignUpState.SelectRegistrationType -> SelectRegistrationType(
                onIndividualClicked = { scope.registerAsIndividual() },
                onOrganisationClicked = { scope.registerAsOrganisation() }
            )
        }
    }
}

fun RBuilder.SignUp(service: PiMonitorService) = child(withRouter(SignUp)) {
    attrs.scope = SignUpScope(service.signUp)
}