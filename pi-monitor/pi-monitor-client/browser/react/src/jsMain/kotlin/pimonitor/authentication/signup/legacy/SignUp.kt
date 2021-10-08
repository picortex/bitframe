package pimonitor.authentication.signup.legacy

import kotlinx.css.minHeight
import kotlinx.css.vh
import pimonitor.PiMonitorService
import pimonitor.authentication.signup.legacy.SignUpState.*
import pimonitor.authentication.signup.exports.SignUpScopeLegacy
import pimonitor.authentication.signup.legacy.SignUpIntent as Intent
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
    var scope: SignUpScopeLegacy
}

private val SignUp = fc<SignUpProps> { props ->
    val scope = props.scope
    val viewModel = scope.viewModel
    val state = useViewModelState(viewModel)
    val history = useHistory()
    Grid {
        css { minHeight = 100.vh }

        when (state) {
            is Loading -> LoadingBox(state.message)
            is Failure -> ErrorBox(state.cause)
            is Success -> SuccessBox(state.message)
            is OrganisationForm -> OrganisationForm(
                fields = state.fields,
                onNext = { viewModel.post(Intent.SubmitBusinessForm(it)) }
            )
            is IndividualForm -> IndividualForm(
                fields = state.fields,
                onNext = { viewModel.post(Intent.SubmitIndividualForm(it)) }
            )
            SelectRegistrationType -> SelectRegistrationType(
                onIndividualClicked = { scope.registerAsIndividual() },
                onOrganisationClicked = { scope.registerAsOrganisation() }
            )
        }
    }
}

fun RBuilder.SignUpLegacy(service: PiMonitorService) = child(withRouter(SignUp)) {
    attrs.scope = SignUpScopeLegacy(service.signUp)
}