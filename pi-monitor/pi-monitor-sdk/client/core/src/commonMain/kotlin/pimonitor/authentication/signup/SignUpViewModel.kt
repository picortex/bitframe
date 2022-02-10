package pimonitor.authentication.signup

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import later.await
import pimonitor.PiMonitorScopeConfig
import pimonitor.authentication.signup.SignUpState.Companion.REGISTER_AS_BUSINESS
import pimonitor.authentication.signup.SignUpState.Companion.REGISTER_AS_INDIVIDUAL
import presenters.feedbacks.FormFeedback.*
import viewmodel.ViewModel
import pimonitor.authentication.signup.SignUpIntent as Intent
import pimonitor.authentication.signup.SignUpState as State

class SignUpViewModel(
    private val config: PiMonitorScopeConfig
) : ViewModel<Intent, State>(State.IndividualForm(IndividualFormFields(), null), config) {
    private val signUpService get() = config.service.signUp
    private val signInService get() = config.service.signIn
    private val recoveryTime get() = config.recoveryTime

    override fun CoroutineScope.execute(i: Intent): Any = when (i) {
        is Intent.ChangeRegistrationType -> updateRegistrationType(i)
        is Intent.Submit -> submitForm(i)
    }

    private fun updateRegistrationType(i: Intent.ChangeRegistrationType) {
        ui.value = when (i.type) {
            REGISTER_AS_BUSINESS.value -> State.BusinessForm(BusinessFormFields(), null)
            REGISTER_AS_INDIVIDUAL.value -> State.IndividualForm(IndividualFormFields(), null)
            else -> {
                logger.warn("Registering as ${i.type} is not supported. Defaulting to Registering as ${REGISTER_AS_INDIVIDUAL.value}")
                State.IndividualForm(IndividualFormFields(), null)
            }
        }
    }

    private fun CoroutineScope.submitForm(i: Intent.Submit) = launch {
        val state = ui.value
        flow {
            emit(state.copy(i, Loading("Creating your account, please wait . . .")))
            signUpService.signUp(i.params).await()
            emit(state.copy(i, Loading("Success. Signing you in, please wait . . .")))
            signInService.signIn(i.params.toCredentials()).await()
            emit(state.copy(i, Success("Successfully signed in")))
        }.catch {
            emit(state.copy(i, Failure(it, "Failed to create your account: ${it.message}")))
            delay(recoveryTime)
            emit(state.copy(i, null))
        }.collect {
            ui.value = it
        }
    }
}