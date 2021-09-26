@file:JsExport

package bitframe.authentication.signIn

import bitframe.authentication.Account
import bitframe.authentication.User
import bitframe.authentication.signin.SignInService
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

    private var loginListener: ((User, Account) -> Unit)? = null

    override fun CoroutineScope.execute(i: SignInIntent): Any = when (i) {
        is SignInIntent.ShowForm -> ui.value = SignInState.Form(i.credentials)
        is SignInIntent.Submit -> signIn(i)
    }

    fun onUserLoggedIn(listener: (User, Account) -> Unit) {
        loginListener = listener
    }

    private fun CoroutineScope.signIn(i: SignInIntent.Submit) = launch {
        flow {
            emit(SignInState.Loading("Signing you in, please wait . . ."))
            val conundrum = service.signIn(i.credentials).await()
            if (conundrum.accounts.size > 1) {
                emit(SignInState.Conundrum(conundrum.user, conundrum.accounts))
            } else {
                val user = conundrum.user
                val account = conundrum.accounts.first()
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