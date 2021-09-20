package pimonitor.authentication.signup

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import later.await
import pimonitor.MonitorParams
import pimonitor.MonitorType
import pimonitor.authentication.SignUpService
import pimonitor.authentication.signup.SignUpIntent.*
import pimonitor.presenters.ButtonInputField
import pimonitor.presenters.TextInputField
import pimonitor.toBusiness
import pimonitor.toPerson
import viewmodel.ViewModel
import kotlin.js.JsExport
import pimonitor.authentication.signup.SignUpIntent as Intent
import pimonitor.authentication.signup.SignUpState as State

@JsExport
class SignUpViewModel(
    private val service: SignUpService
) : ViewModel<Intent, State>(State.SelectRegistrationType) {

    private val recoveryTime = 3000

    private val individualFormParams = NameEmailFormParams(
        title = "Enter your personal information",
        name = TextInputField("Name", "John Doe"),
        email = TextInputField("Email", "john@doe.com"),
        nextButton = ButtonInputField("Submit"),
        prevButton = ButtonInputField("Back") { post(SelectRegistrationType) }
    )

    private val organisationFormParams = NameEmailFormParams(
        title = "Enter your organisation's information",
        name = TextInputField("Organisation's Name", "John Doe Enterprises"),
        email = TextInputField("Organisation's Email", "support@enterpries.com"),
        nextButton = ButtonInputField("Submit"),
        prevButton = ButtonInputField("Back") { post(SelectRegistrationType) }
    )

    override fun CoroutineScope.execute(i: Intent): Any = when (i) {
        SelectRegistrationType -> ui.value = State.SelectRegistrationType
        is RegisterAsIndividual -> ui.value = State.NameEmailForm(
            params = i.params ?: individualFormParams,
            prevParams = null
        )
        is RegisterAsOrganization -> ui.value = State.NameEmailForm(
            params = i.params ?: organisationFormParams,
            prevParams = null
        )
        is SubmitForm -> submitForm(i)
    }

    private fun CoroutineScope.submitForm(i: SubmitForm) = launch {
        val state = ui.value
        try {
            val form = state as? State.NameEmailForm ?: throw IllegalStateException("Invalid state")
            if (form.params.title == organisationFormParams.title) {
                submitBusinessInfo(i.params, form)
            } else {
                submitPersonalInfo(i.params, form)
            }
        } catch (cause: Throwable) {
            ui.value = State.Failure(cause)
            delay(recoveryTime.toLong())
            ui.value = state
        }
    }

    private fun CoroutineScope.submitPersonalInfo(personInfo: MonitorParams, state: State.NameEmailForm) = launch {
        flow {
            emit(State.Loading("Submitting your registration, Please wait . . ."))
            val person = personInfo.toPerson()
            val prevParams = state.prevParams
            if (prevParams != null && prevParams.title == organisationFormParams.title) {
                val business = MonitorParams(prevParams.name.value, prevParams.email.value).toBusiness()
                service.register(business, representedBy = person).await()
            } else {
                service.registerIndividuallyAs(person).await()
            }
            emit(State.Success("Registration completed successfully"))
        }.catch {
            emit(State.Failure(it, "Registration failed"))
            delay(recoveryTime.toLong())
            emit(state.withValuesOf(personInfo))
        }.collect {
            ui.value = it
        }
    }

    private fun CoroutineScope.submitBusinessInfo(
        businessInfo: MonitorParams,
        state: State.NameEmailForm
    ) = launch {
        flow {
            emit(State.Loading("Validating business input"))
            businessInfo.toBusiness()
            val prevParams = state.params.withValuesOf(businessInfo)
            val params = individualFormParams.copy().apply {
                prevButton.handler = { ui.value = State.NameEmailForm(prevParams, null) }
            }
            emit(State.NameEmailForm(params, prevParams))
        }.catch {
            emit(State.Failure(it, "Validation Failed"))
            delay(recoveryTime.toLong())
            emit(state.withValuesOf(businessInfo))
        }.collect {
            ui.value = it
        }
    }

    private fun State.NameEmailForm.withValuesOf(info: MonitorParams) = copy(
        params = params.withValuesOf(info)
    )

    private fun NameEmailFormParams.withValuesOf(info: MonitorParams) = copy(
        name = name.copy(value = info.name),
        email = email.copy(value = info.email)
    )
}