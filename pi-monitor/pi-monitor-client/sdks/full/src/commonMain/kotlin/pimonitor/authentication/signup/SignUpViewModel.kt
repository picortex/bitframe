package pimonitor.authentication.signup

import bitframe.presenters.feedbacks.FormFeedback.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import later.await
import viewmodel.ViewModel
import pimonitor.authentication.signup.SignUpIntent as Intent
import pimonitor.authentication.signup.SignUpState as State

class SignUpViewModel(
    val service: SignUpService
) : ViewModel<Intent, State>(State.IndividualForm(IndividualFormFields(), null)) {
    val recoveryTime = 3000L

    override fun CoroutineScope.execute(i: Intent): Any = when (i) {
        is Intent.SelectRegisterAsIndividual -> selectRegisterAsIndividual(i)
        is Intent.SelectRegisterAsBusiness -> selectRegisterAsBusiness(i)
        is Intent.Submit.IndividualForm -> submitForm(i)
        is Intent.Submit.BusinessForm -> submitForm(i)
    }

    private fun CoroutineScope.submitForm(i: Intent.Submit) = launch {
        val state = ui.value
        flow {
            emit(state.copy(i, Loading("Creating your account, please wait . . .")))
            service.signUp(i.params).await()
            emit(state.copy(i, Success("Your registration completed successfully")))
        }.catch {
            emit(state.copy(i, Failure(it, "Failed to create your account")))
            delay(recoveryTime)
            emit(state.copy(i, null))
        }.collect {
            ui.value = it
        }
    }

    private fun selectRegisterAsIndividual(i: Intent.SelectRegisterAsIndividual) {
        ui.value = State.IndividualForm(IndividualFormFields(), null)
    }

    private fun selectRegisterAsBusiness(i: Intent.SelectRegisterAsBusiness) {
        ui.value = State.BusinessForm(BusinessFormFields(), null)
    }
}