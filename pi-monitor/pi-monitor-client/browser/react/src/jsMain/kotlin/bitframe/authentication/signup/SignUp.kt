package bitframe.authentication.signup

import bitframe.PanelPageRoute
import bitframe.components.TextInput
import bitframe.presenters.feedbacks.FormFeedback
import kotlinx.css.*
import kotlinx.extensions.onDesktop
import kotlinx.extensions.onMobile
import kotlinx.extensions.text
import kotlinx.html.InputType
import bitframe.client.PiMonitorService
import bitframe.authentication.signup.exports.SignUpScope
import react.Props
import react.RBuilder
import react.dom.p
import react.fc
import react.router.dom.useHistory
import react.router.dom.withRouter
import reakt.*
import bitframe.reakt.DropDown
import styled.css
import styled.styledH2
import theme.clazz
import useViewModelState
import bitframe.authentication.signup.SignUpIntent as Intent

private external class SignUpProps : Props {
    var scope: SignUpScope
}

private val SignUp = fc<SignUpProps> { props ->
    val scope = props.scope
    val useSignInEvent = scope.useSignInEvent
    val viewModel = scope.viewModel
    val state = useViewModelState(viewModel)
    val history = useHistory()
    useSignInEvent {
        history.push(PanelPageRoute)
    }
    FlexBox {
        css {
            minHeight = 100.vh
            centerContent()
            onMobile { padding(horizontal = 1.em) }
            onDesktop { padding(horizontal = 20.pct) }
        }

        Form { theme ->
            styledH2 {
                css { +theme.text.h4.clazz }
                +state.title
            }

            DropDown(
                name = "registrationType",
                value = state.select.selected?.value,
                options = state.select.options,
                onChange = scope.changeRegistrationType
            )

            when (state) {
                is SignUpState.IndividualForm -> {
                    val fields = state.fields
                    TextInput("name", fields.name)

                    TextInput("email", fields.email, InputType.email)

                    TextInput("password", fields.password, InputType.password)
                }
                is SignUpState.BusinessForm -> {
                    val fields = state.fields
                    TextInput("businessName", fields.businessName)

                    TextInput("individualName", fields.individualName)

                    TextInput("individualEmail", fields.individualEmail, InputType.email)

                    TextInput("password", fields.password, InputType.password)
                }
            }

            when (val status = state.status) {
                is FormFeedback.Loading -> p { +status.message }
                is FormFeedback.Failure -> p { +status.message }
                is FormFeedback.Success -> p { +status.message }
                null -> ContainedButton(state.submitButton.text)
            }

        } onSubmit {
            when (state) {
                is SignUpState.IndividualForm -> {
                    val name by text()
                    val email by text()
                    val password by text()
                    val params = SignUpParams.Individual(name, email, password)
                    viewModel.post(Intent.Submit.IndividualForm(params))
                }
                is SignUpState.BusinessForm -> {
                    val businessName by text()
                    val individualName by text()
                    val individualEmail by text()
                    val password by text()
                    val params = SignUpParams.Business(businessName, individualName, individualEmail, password)
                    viewModel.post(Intent.Submit.BusinessForm(params))
                }
            }
        }
    }
}

fun RBuilder.SignUp(service: PiMonitorService) = child(withRouter(SignUp)) {
    attrs.scope = SignUpScope(service)
}