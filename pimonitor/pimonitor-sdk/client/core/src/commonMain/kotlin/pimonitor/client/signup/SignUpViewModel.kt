package pimonitor.client.signup

import bitframe.client.UIScopeConfig
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import later.await
import pimonitor.client.PiMonitorApi
import pimonitor.client.signup.SignUpIntent.Submit.BusinessForm
import pimonitor.client.signup.SignUpIntent.Submit.IndividualForm
import pimonitor.client.signup.SignUpState.Companion.REGISTER_AS_BUSINESS
import pimonitor.client.signup.SignUpState.Companion.REGISTER_AS_INDIVIDUAL
import pimonitor.client.signup.fields.BusinessFormFields
import pimonitor.client.signup.fields.IndividualFormFields
import presenters.cases.Feedback.*
import viewmodel.ViewModel
import pimonitor.client.signup.SignUpIntent as Intent
import pimonitor.client.signup.SignUpState as State

class SignUpViewModel(
    private val config: UIScopeConfig<PiMonitorApi>
) : ViewModel<Intent, State>(State.IndividualForm(IndividualFormFields(), null), config.viewModel) {
    private val signUpService get() = config.service.signUp
    private val signInService get() = config.service.signIn
    private val recoveryTime get() = config.viewModel.recoveryTime

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
            when (i) {
                is IndividualForm -> signUpService.signUp(i.params)
                is BusinessForm -> signUpService.signUp(i.params)
            }.await()
            emit(state.copy(i, Loading("Success. Signing you in, please wait . . .")))
            when (i) {
                is IndividualForm -> signInService.signIn(i.params.toSignInCredentials()).await()
                is BusinessForm -> signInService.signIn(i.params.toSignInCredentials()).await()
            }
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