package bitframe.authentication.signin

import bitframe.authentication.client.signin.SignInService
import presenters.feedbacks.FormFeedback.*
import cache.Cache
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import later.await
import viewmodel.ViewModel

class SignInViewModel(
    private val service: SignInService,
    private val cache: Cache
) : ViewModel<SignInIntent, SignInState>(SignInState.Form(SignInFormFields(), null)) {

    private val recoveryTime = 3000

    override fun CoroutineScope.execute(i: SignInIntent): Any = when (i) {
        is SignInIntent.InitForm -> initialize()
        is SignInIntent.Submit -> signIn(i)
        is SignInIntent.Resolve -> resolve(i)
    }

    private fun CoroutineScope.initialize() = launch {
        val state = SignInState.Form(SignInFormFields(), null)
        flow {
            emit(state.copy(status = Loading("Fetching your previous credentials")))
            val cred = cache.load<SignInCredentials>(SignInService.CREDENTIALS_CACHE_KEY).await()
            emit(state.copy(fields = SignInFormFields.with(cred)))
        }.catch {
            emit(state)
        }.collect {
            ui.value = it
        }
    }

    private fun resolve(i: SignInIntent.Resolve) {
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
                emit(state.copy(status = Loading("Saving your session for next login")))
                cache.save(SignInService.SESSION_CACHE_KEY, service.currentSession)
                emit(state.copy(status = Success("Logged in successfully")))
            }
        }.catch {
            emit(state.copy(status = Failure(it)))
            delay(recoveryTime.toLong())
            emit(state.copy(i, null))
        }.collect { ui.value = it }
    }
}