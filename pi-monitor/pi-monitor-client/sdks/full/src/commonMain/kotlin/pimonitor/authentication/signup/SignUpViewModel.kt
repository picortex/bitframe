package pimonitor.authentication.signup

import bitframe.authentication.signin.SignInService
import bitframe.presenters.feedbacks.FormFeedback.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import later.await
import pimonitor.monitors.toCredentials
import viewmodel.ViewModel
import pimonitor.authentication.signup.SignUpIntent as Intent
import pimonitor.authentication.signup.SignUpState as State

class SignUpViewModel(
    val signUpService: SignUpService,
    val signInService: SignInService
) : ViewModel<Intent, State>(State.IndividualForm(IndividualFormFields(), null)) {
    val recoveryTime = 3000L

    override fun CoroutineScope.execute(i: Intent): Any = when (i) {
        Intent.SelectRegisterAsIndividual -> selectRegisterAsIndividual()
        Intent.SelectRegisterAsBusiness -> selectRegisterAsBusiness()
        is Intent.Submit -> submitForm(i)
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
            emit(state.copy(i, Failure(it, "Failed to create your account")))
            delay(recoveryTime)
            emit(state.copy(i, null))
        }.collect {
            ui.value = it
        }
    }

    private fun selectRegisterAsIndividual() {
        ui.value = State.IndividualForm(IndividualFormFields(), null)
    }

    private fun selectRegisterAsBusiness() {
        ui.value = State.BusinessForm(BusinessFormFields(), null)
    }
}