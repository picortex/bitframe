package bitframe.authentication.signin

import bitframe.presenters.feedbacks.FormFeedback.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import later.await
import logging.logger
import viewmodel.ViewModel
import kotlin.js.JsExport

class SignInViewModel(
    private val service: SignInService
) : ViewModel<SignInIntent, SignInState>(SignInState(SignInFormFields(), null)) {

    private val recoveryTime = 3000

    val console = logger()

    override fun CoroutineScope.execute(i: SignInIntent): Any = when (i) {
        is SignInIntent.Submit -> signIn(i)
    }

    private fun CoroutineScope.signIn(i: SignInIntent.Submit) = launch {
        val state = ui.value.copy(status = Loading("Signing you in, please wait . . ."))
        flow {
            emit(state)
            val conundrum = service.signIn(i.credentials).await()
            if (conundrum.spaces.size > 1) {
                console.warn("User has more than one Space, ")
                emit(state.copy(status = Success("Logged in successfully")))
            } else {
                emit(state.copy(status = Success("Logged in successfully")))
            }
        }.catch {
            emit(state.copy(status = Failure(it)))
            delay(recoveryTime.toLong())
            emit(state.copy(i, null))
        }.collect { ui.value = it }
    }
}