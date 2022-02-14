package bitframe.client.panel

import bitframe.client.UIScopeConfig
import bitframe.client.signin.SignInService
import bitframe.core.Session
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import later.await
import live.WatchMode.Companion.EAGERLY
import live.watch
import viewmodel.ViewModel
import bitframe.client.panel.PanelIntent as Intent
import bitframe.client.panel.PanelState as State

class PanelViewModel(
    config: UIScopeConfig<SignInService>
) : ViewModel<Intent, State>(State.Loading("Setting up your workspace"), config.viewModel) {
    val service = config.service

    override fun CoroutineScope.execute(i: Intent) = when (i) {
        Intent.InitPanel -> initialize()
    }

    private fun watchSessionAndUpdateUI() = coroutineScope.watch(service.session, EAGERLY) {
        when (it) {
            is Session.SignedOut -> ui.value = State.Login
            is Session.SignedIn -> ui.value = State.Panel(it)
            else -> {}
        }
    }

    private fun CoroutineScope.initialize() = launch {
        flow {
            emit(State.Loading("Retrieving your last session"))
            val signInSession = service.currentSession
            if (signInSession is Session.SignedIn) {
                emit(State.Panel(signInSession))
            } else {
                emit(State.Loading("Re authenticating you with your last session"))
                val session = service.signInWithLastSession().await() ?: error("Couldn't sign in with last session")
                emit(State.Panel(session))
            }
        }.catch {
            emit(State.Login)
        }.collect {
            ui.value = it
        }
        watchSessionAndUpdateUI()
    }
}