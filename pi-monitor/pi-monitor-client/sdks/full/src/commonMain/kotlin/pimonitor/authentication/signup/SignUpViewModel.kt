package pimonitor.authentication.signup

import contacts.Email
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import pimonitor.Monitor
import pimonitor.authentication.signup.SignUpIntent.Stage01
import pimonitor.authentication.signup.SignUpIntent.Stage02
import pimonitor.authentication.signup.SignUpState.Form
import viewmodel.ViewModel
import kotlin.js.JsExport

@JsExport
class SignUpViewModel : ViewModel<SignUpIntent, SignUpState>(Form.Stage01(null)) {

    override fun CoroutineScope.execute(i: SignUpIntent): Any = when (i) {
        is Stage01 -> stage01()
        is Stage02 -> stage02(i)
    }

    private fun CoroutineScope.stage02(i: Stage02) = launch {
        flow<SignUpState> {
            val business = Monitor.Business(
                name = i.business.name ?: error("Invalid business name: ${i.business.name}"),
                email = Email(i.business.email ?: error("Invalid business email: ${i.business.email}"))
            )
            emit(Form.Stage02(business))
        }.catch {
            emit(SignUpState.Error(it))
        }.collect {
            ui.value = it
        }
    }

    private fun stage01() = when (val state = ui.value) {
        is Form.Stage02 -> ui.value = Form.Stage01(state.business)
        else -> ui.value = Form.Stage01(null)
    }
}