package pimonitor.authentication.signup

import bitframe.PanelPageRoute
import bitframe.components.TextInput
import presenters.feedbacks.FormFeedback
import kotlinx.css.*
import kotlinx.extensions.onDesktop
import kotlinx.extensions.onMobile
import kotlinx.extensions.text
import kotlinx.html.InputType
import pimonitor.PiMonitorReactScope
import pimonitor.authentication.signup.exports.SignUpReactScope
import react.Props
import react.RBuilder
import react.dom.p
import react.fc
import reakt.*
import pimonitor.reakt.DropDown
import react.router.useNavigate
import styled.css
import styled.styledH2
import theme.clazz
import useViewModelState
import pimonitor.authentication.signup.SignUpIntent as Intent

private external class SignUpProps : Props {
    var scope: SignUpReactScope
}

private val SignUp = fc<SignUpProps> { props ->
    val scope = props.scope
    val useSignInEvent = scope.useSignInEvent
    val viewModel = scope.viewModel
    val state = useViewModelState(viewModel)
    val navigate = useNavigate()
    useSignInEvent {
        navigate(PanelPageRoute)
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
                    val params = RawIndividualSignUpParams(name, email, password)
                    viewModel.post(Intent.Submit.IndividualForm(params))
                }
                is SignUpState.BusinessForm -> {
                    val businessName by text()
                    val individualName by text()
                    val individualEmail by text()
                    val password by text()
                    val params = RawBusinessSignUpParams(businessName, individualName, individualEmail, password)
                    viewModel.post(Intent.Submit.BusinessForm(params))
                }
            }
        }
    }
}

fun RBuilder.SignUp(scope: PiMonitorReactScope) = SignUp {
    attrs.scope = scope.signUp
}