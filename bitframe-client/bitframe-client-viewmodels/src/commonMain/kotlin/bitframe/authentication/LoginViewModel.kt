package bitframe.authentication

import bitframe.authentication.LoginViewModel.Intent
import bitframe.authentication.LoginViewModel.State
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import viewmodel.ViewModel

class LoginViewModel(val service: LoginService) : ViewModel<Intent, State>(State.Form(null)) {
    sealed interface State {
        data class Form(val credentials: Credentials?) : State
        data class Conundrum(val user: User, val accounts: List<Account>) : State
        data class Loading(val message: String) : State
        data class Error(val throwable: Throwable) : State
    }

    sealed interface Intent {
        data class ShowForm(val credentials: Credentials?) : Intent
        data class Login(val credentials: Credentials) : Intent
    }

    override fun CoroutineScope.execute(i: Intent): Any = when (i) {
        is Intent.ShowForm -> ui.value = State.Form(i.credentials)
        is Intent.Login -> login()
    }

    private fun CoroutineScope.login() = launch {

    }
}