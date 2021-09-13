package pimonitor.authentication.signup

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import later.await
import pimonitor.authentication.SignUpService
import pimonitor.authentication.signup.SignUpIntent.*
import pimonitor.authentication.signup.SignUpState.Form
import pimonitor.toBusiness
import pimonitor.toPerson
import viewmodel.ViewModel
import kotlin.js.JsExport
import pimonitor.authentication.signup.SignUpState as State

@JsExport
class SignUpViewModel(
    private val service: SignUpService
) : ViewModel<SignUpIntent, State>(Form.Stage01(null)) {

    private val recoveryTime = 3000

    override fun CoroutineScope.execute(i: SignUpIntent): Any = when (i) {
        is Stage01 -> stage01()
        is Stage02 -> stage02(i)
        is Submit -> submit(i)
    }

    private fun CoroutineScope.submit(i: Submit) = launch {
        var recover = ui.value
        flow {
            val stage02 = recover as? Form.Stage02 ?: throw IllegalStateException("Stop trying to register without using the registration form")
            recover = stage02.copy(person = i.person)
            val business = stage02.business.toBusiness()
            val person = i.person.toPerson()
            emit(State.Loading("Signing you up, Please wait . . ."))
            service.register(business, person).await()
            emit(State.Success("Your registration completed successfully"))
        }.catch {
            emit(State.Failure(it))
            delay(recoveryTime.toLong())
            emit(recover)
        }.collect { ui.value = it }
    }

    private fun CoroutineScope.stage02(i: Stage02) = launch {
        var recover = ui.value
        flow<State> {
            recover = Form.Stage01(i.business)
            // Ensure that it is a valid business
            i.business.toBusiness()
            emit(Form.Stage02(i.business, null))
        }.catch {
            emit(State.Failure(it))
            delay(recoveryTime.toLong())
            emit(recover)
        }.collect {
            ui.value = it
        }
    }

    private fun stage01() = when (val state = ui.value) {
        is Form.Stage02 -> ui.value = Form.Stage01(state.business)
        else -> ui.value = Form.Stage01(null)
    }
}