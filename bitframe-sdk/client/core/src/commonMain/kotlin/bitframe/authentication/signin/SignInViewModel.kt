package bitframe.authentication.signin

import bitframe.authentication.client.signin.SignInService
import bitframe.client.BitframeViewModelConfig
import presenters.feedbacks.FormFeedback.*
import cache.Cache
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import later.await
import live.value
import viewmodel.ViewModel
import bitframe.authentication.signin.SignInState as State

class SignInViewModel(
    config: BitframeViewModelConfig
) : ViewModel<SignInIntent, State>(State.Form(SignInFormFields(), null), config) {

    private val service: SignInService = config.service.signIn
    private val cache: Cache = config.service.config.cache
    private val recoveryTime = config.recoveryTime

    override fun CoroutineScope.execute(i: SignInIntent): Any = when (i) {
        is SignInIntent.InitForm -> initialize()
        is SignInIntent.Submit -> signIn(i)
        is SignInIntent.ResolveConundrum -> resolveConundrum(i)
    }

    private fun CoroutineScope.initialize() = launch {
        val state = State.Form(SignInFormFields(), null)
        flow<State> {
            emit(state.copy(status = Loading("Fetching your previous credentials")))
            val cred = cache.load<SignInCredentials>(SignInService.CREDENTIALS_CACHE_KEY).await()
            emit(state.copy(fields = SignInFormFields.with(cred)))
        }.catch {
            emit(state)
        }.collect {
            ui.value = it
        }
    }

    private fun CoroutineScope.resolveConundrum(i: SignInIntent.ResolveConundrum) = launch {
        flow<State> {
            val state = ui.value as? State.Conundrum ?: error("Do not resolve a conundrum while not in a conundrum state")
            emit(state.copy(status = Loading("Logging into ${i.space.name}")))
            service.resolveConundrum(i.space).await()
            emit(state.copy(status = Success()))
        }.catch {
            emit(ui.value.copy(it))
        }.collect {
            ui.value = it
        }
    }

    private fun CoroutineScope.signIn(i: SignInIntent.Submit) = launch {
        val state = ui.value as State.Form
        flow {
            emit(state.copy(status = Loading("Signing you in, please wait . . .")))
            val conundrum = service.signIn(i.credentials).await()
            if (conundrum.spaces.size > 1) {
                emit(State.Conundrum(conundrum.user, conundrum.spaces, null))
            } else {
                emit(state.copy(status = Loading("Saving your session for next login")))
                cache.save(SignInService.SESSION_CACHE_KEY, service.currentSession)
                emit(state.copy(status = Success("Logged in successfully")))
            }
        }.catch {
            emit(state.copy(status = Failure(it)))
            delay(recoveryTime)
            emit(state.copy(i, null))
        }.collect { ui.value = it }
    }
}