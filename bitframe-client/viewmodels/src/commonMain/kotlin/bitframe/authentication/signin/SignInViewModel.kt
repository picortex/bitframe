@file:JsExport

package bitframe.authentication.signin

import bitframe.authentication.spaces.Space
import bitframe.authentication.users.User
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

    private var loginListener: ((User, Space) -> Unit)? = null

    private val recoveryTime = 3000

    private val logger = logger()

    override fun CoroutineScope.execute(i: SignInIntent): Any = when (i) {
        is SignInIntent.Submit -> signIn(i)
    }

    fun onUserLoggedIn(listener: (User, Space) -> Unit) {
        loginListener = listener
    }

    private fun CoroutineScope.signIn(i: SignInIntent.Submit) = launch {
        val state = ui.value.copy(status = Loading("Signing you in, please wait . . ."))
        flow {
            emit(state)
            val conundrum = service.signIn(i.credentials).await()
            if (conundrum.spaces.size > 1) {
                logger.warn("User has more than one Space, ")
                emit(state.copy(status = Success("Logged in successfully")))
                loginListener?.invoke(conundrum.user, conundrum.spaces.first())
            } else {
                val user = conundrum.user
                val account = conundrum.spaces.first()
                emit(state.copy(status = Success("Logged in successfully")))
                loginListener?.invoke(user, account)
            }
        }.catch {
            emit(state.copy(status = Failure(it)))
            delay(recoveryTime.toLong())
            emit(state.copy(i))
        }.collect { ui.value = it }
    }
}