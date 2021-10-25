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
) : ViewModel<SignInIntent, SignInState>(SignInState.Form(SignInFormFields(), null)) {

    private val recoveryTime = 3000

    override fun CoroutineScope.execute(i: SignInIntent): Any = when (i) {
        is SignInIntent.Submit -> signIn(i)
        is SignInIntent.Resolve -> resolve(i)
    }

    private fun resolve(i: SignInIntent.Resolve) {
        val state = ui.value as SignInState.Conundrum
        service.resolve(i.space)
    }

    private fun CoroutineScope.signIn(i: SignInIntent.Submit) = launch {
        val state = ui.value as SignInState.Form
        flow {
            emit(state.copy(status = Loading("Signing you in, please wait . . .")))
            val conundrum = service.signIn(i.credentials).await()
            if (conundrum.spaces.size > 1) {
                emit(SignInState.Conundrum(conundrum.user, conundrum.spaces))
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