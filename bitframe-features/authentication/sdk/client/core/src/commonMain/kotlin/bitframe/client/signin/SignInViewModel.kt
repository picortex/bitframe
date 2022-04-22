package bitframe.client.signin

import bitframe.client.UIScopeConfig
import bitframe.core.signin.SignInParams
import bitframe.core.signin.SignInRawParams
import presenters.cases.Feedback.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import later.await
import viewmodel.ViewModel
import bitframe.client.signin.SignInState as State

class SignInViewModel(
    private val config: UIScopeConfig<SignInService>
) : ViewModel<SignInIntent, State>(State.Form(SignInForm { onSubmit { } }), config.viewModel) {

    private val service get() = config.service
    private val recoveryTime get() = config.viewModel.recoveryTime

    override fun CoroutineScope.execute(i: SignInIntent): Any = when (i) {
        is SignInIntent.InitForm -> initialize()
        is SignInIntent.Submit -> signIn(i)
        is SignInIntent.ResolveConundrum -> resolveConundrum(i)
    }

    internal fun form(email: String? = null, password: String? = null) = SignInForm(email, password) { onSubmit("Sign In") { post(SignInIntent.Submit(it)) } }

    internal fun form(cred: SignInRawParams) = form(
        email = cred.email ?: cred.identifier,
        password = cred.password
    )

    private fun CoroutineScope.initialize() = launch {
        val state = State.Form(status = None, data = form())
        flow {
            emit(state.copy(status = Loading("Fetching your previous credentials")))
            val cred = service.loadCachedCredentials().await()
            emit(state.copy(status = None, data = form(cred)))
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
            val conundrum = service.signIn(i.params).await()
            if (conundrum.spaces.size > 1) {
                emit(State.Conundrum(conundrum.user, conundrum.spaces, None))
            } else {
                emit(state.copy(status = Success("Logged in successfully")))
            }
        }.catch {
            emit(state.copy(status = Failure(it)))
            delay(recoveryTime)
            emit(state.copy(status = None, data = form(i.params)))
        }.collect { ui.value = it }
    }
}