package bitframe.authentication

import bitframe.authentication.LoginViewModel.Intent
import bitframe.authentication.LoginViewModel.State
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import later.await
import viewmodel.ViewModel
import kotlin.js.JsExport
import kotlin.js.JsName

@JsExport
class LoginViewModel(
    private val service: SignInService
) : ViewModel<Intent, State>(State.Form(null)) {

    private var loginListener: ((User, Account) -> Unit)? = null

    @Deprecated("Use UI Directly")
    @JsName("state")
    val state
        get() = ui

    sealed class State {
        data class Form(val credentials: LoginCredentials?) : State()
        data class Conundrum(val user: User, val accounts: List<Account>) : State()
        data class Loading(val message: String) : State()
        data class Error(val throwable: Throwable) : State()
        data class Success(val message: String) : State()
    }

    sealed class Intent {
        data class ShowForm(val credentials: LoginCredentials?) : Intent()
        data class Login(val credentials: LoginCredentials) : Intent()
    }

    override fun CoroutineScope.execute(i: Intent): Any = when (i) {
        is Intent.ShowForm -> ui.value = State.Form(i.credentials)
        is Intent.Login -> login(i)
    }

    fun onUserLoggedIn(listener: (User, Account) -> Unit) {
        loginListener = listener
    }

    private fun CoroutineScope.login(i: Intent.Login) = launch {
        ui.value = State.Loading("Singing you in, please wait . . .")
        flow {
            val conundrum = service.loginWith(i.credentials).await()
            if (conundrum.accounts.size > 1) {
                emit(State.Conundrum(conundrum.user, conundrum.accounts))
            } else {
                val user = conundrum.user
                val account = conundrum.accounts.first()
                emit(State.Success("Logged in successfully"))
                loginListener?.invoke(user, account)
            }
        }.catch {
            emit(State.Error(it))
        }.collect {
            ui.value = it
        }
    }
}