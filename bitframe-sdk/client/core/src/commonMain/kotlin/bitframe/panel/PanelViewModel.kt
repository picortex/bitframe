package bitframe.panel

import bitframe.authentication.signin.Session
import bitframe.client.BitframeViewModelConfig
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import later.await
import live.Watcher
import viewmodel.ViewModel
import bitframe.panel.PanelIntent as Intent
import bitframe.panel.PanelState as State

class PanelViewModel(
    config: BitframeViewModelConfig
) : ViewModel<Intent, State>(State.Loading("Setting up your workspace"), config) {
    val service = config.service.signIn

    var sessionWatcher: Watcher<*>? = null

    override fun CoroutineScope.execute(i: Intent) = when (i) {
        Intent.InitPanel -> initialize()
    }

    private fun watchSessionAndUpdateUI() {
        sessionWatcher = service.session.watch(ignoreImmediateValue = true) {
            val oldState = ui.value as? State.Panel
            when {
                it is Session.SignedOut -> ui.value = State.Login
                it is Session.SignedIn && it != oldState?.session -> ui.value = State.Panel(it, sections)
            }
        }
    }

    private fun CoroutineScope.initialize() = launch {
        flow {
            emit(State.Loading("Retrieving your last session"))
            val signInSession = service.currentSession
            if (signInSession is Session.SignedIn) {
                emit(State.Panel(signInSession, sections))
            } else {
                emit(State.Loading("Re authenticating you with your last session"))
                val session = service.signInWithLastSession().await() ?: error("Couldn't sign in with last session")
                emit(State.Panel(session, sections))
            }
        }.catch {
            emit(State.Login)
        }.collect {
            ui.value = it
        }
        watchSessionAndUpdateUI()
    }

    override fun onCleared() {
        super.onCleared()
        sessionWatcher?.stop()
    }
}