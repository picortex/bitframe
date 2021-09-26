@file:JsExport

package bitframe.authentication.signin

import bitframe.authentication.spaces.Space
import bitframe.authentication.users.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import later.await
import viewmodel.ViewModel
import kotlin.js.JsExport

class SignInViewModel(
    private val service: SignInService
) : ViewModel<SignInIntent, SignInState>(SignInState.Form(null)) {

    private var loginListener: ((User, Space) -> Unit)? = null

    override fun CoroutineScope.execute(i: SignInIntent): Any = when (i) {
        is SignInIntent.ShowForm -> ui.value = SignInState.Form(i.credentials)
        is SignInIntent.Submit -> signIn(i)
    }

    fun onUserLoggedIn(listener: (User, Space) -> Unit) {
        loginListener = listener
    }

    private fun CoroutineScope.signIn(i: SignInIntent.Submit) = launch {
        flow {
            emit(SignInState.Loading("Signing you in, please wait . . ."))
            val conundrum = service.signIn(i.credentials).await()
            if (conundrum.spaces.size > 1) {
                emit(SignInState.Conundrum(conundrum.user, conundrum.spaces))
            } else {
                val user = conundrum.user
                val account = conundrum.spaces.first()
                emit(SignInState.Success("Logged in successfully"))
                loginListener?.invoke(user, account)
            }
        }.catch {
            emit(SignInState.Failure(it))
        }.collect {
            ui.value = it
        }
    }
}