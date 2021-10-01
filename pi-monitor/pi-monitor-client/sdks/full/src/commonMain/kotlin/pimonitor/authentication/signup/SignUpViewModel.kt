package pimonitor.authentication.signup

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import later.await
import bitframe.presenters.fields.ButtonInputField
import pimonitor.authentication.signup.SignUpIntent.*
import pimonitor.toBusiness
import pimonitor.toPerson
import viewmodel.ViewModel
import pimonitor.authentication.signup.SignUpIntent as Intent
import pimonitor.authentication.signup.SignUpState as State

class SignUpViewModel(
    private val service: SignUpService
) : ViewModel<Intent, State>(State.SelectRegistrationType) {

    private val recoveryTime = 3000

    override fun CoroutineScope.execute(i: Intent): Any = when (i) {
        SelectRegistrationType -> ui.value = State.SelectRegistrationType
        is RegisterAsIndividual -> ui.value = State.IndividualForm(
            fields = i.fields ?: IndividualFormFields(
                prevButton = ButtonInputField("Back") { post(SelectRegistrationType) }
            ),
            organisationForm = null
        )
        is RegisterAsOrganization -> ui.value = State.OrganisationForm(
            fields = i.fields ?: OrganisationFormFields(
                prevButton = ButtonInputField("Back") { post(SelectRegistrationType) }
            )
        )
        is SubmitIndividualForm -> submitPersonalInfo(i, ui.value as State.IndividualForm)
        is SubmitBusinessForm -> submitBusinessInfo(i, ui.value as State.OrganisationForm)
    }

    private fun CoroutineScope.submitPersonalInfo(
        i: SubmitIndividualForm,
        state: State.IndividualForm
    ) = launch {
        flow {
            emit(State.Loading("Submitting your registration, Please wait . . ."))
            val person = i.params.toPerson()
            val organisationFields = state.organisationForm?.fields
            if (organisationFields != null) {
                val business = organisationFields.toParams().toBusiness()
                service.register(business, representedBy = person).await()
            } else {
                service.registerIndividuallyAs(i.params).await()
            }
            emit(State.Success("Registration completed successfully"))
        }.catch {
            emit(State.Failure(it, "Registration failed"))
            delay(recoveryTime.toLong())
            emit(state.copy(fields = state.fields.copy(i.params)))
        }.collect {
            ui.value = it
        }
    }

    private fun CoroutineScope.submitBusinessInfo(
        i: SubmitBusinessForm,
        state: State.OrganisationForm
    ) = launch {
        flow {
            emit(State.Loading("Validating business input"))
            i.params.toBusiness()
            val params = IndividualFormFields(
                prevButton = ButtonInputField("Back") {
                    ui.value = state.copy(fields = state.fields.copy(i.params))
                }
            )
            emit(State.IndividualForm(params, state.copy(fields = state.fields.copy(i.params))))
        }.catch {
            emit(State.Failure(it, "Validation Failed"))
            delay(recoveryTime.toLong())
            emit(state.copy(fields = state.fields.copy(i.params)))
        }.collect {
            ui.value = it
        }
    }
}