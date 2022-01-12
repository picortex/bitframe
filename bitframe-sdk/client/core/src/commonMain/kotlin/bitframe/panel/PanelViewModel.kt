package bitframe.panel

import bitframe.api.BitframeService
import bitframe.api.BitframeServiceConfig
import bitframe.authentication.client.signin.SignInService
import bitframe.authentication.signin.Session
import bitframe.authentication.signin.SignInCredentials
import bitframe.client.BitframeViewModelConfig
import cache.Cache
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import later.await
import viewmodel.ViewModel
import bitframe.panel.PanelIntent as Intent
import bitframe.panel.PanelState as State

class PanelViewModel(
    config: BitframeViewModelConfig
) : ViewModel<Intent, State>(State.Loading("Setting up your workspace"), config) {
    val service: BitframeService = config.service
    val cache: Cache = config.service.cache

    override fun CoroutineScope.execute(i: Intent) = when (i) {
        Intent.InitPanel -> initialize()
    }

    private fun CoroutineScope.initialize() = launch {
        flow {
            emit(State.Loading("Retrieving your last session"))
            val signInSession = service.signIn.currentSession
            if (signInSession is Session.SignedIn) {
                emit(State.Panel(signInSession, sections))
            } else {
                val cred = cache.load<SignInCredentials>(SignInService.CREDENTIALS_CACHE_KEY).await()
                emit(State.Loading("Re authenticating you with your last session"))
                val res = service.signIn.signIn(cred).await()
                if (res.spaces.size != 1) {
                    val session = cache.load<Session.SignedIn>(SignInService.SESSION_CACHE_KEY).await()
                    service.signIn.resolve(session.space)
                }
                val session = service.signIn.currentSession as Session.SignedIn
                emit(State.Panel(session, sections))
            }
        }.catch {
            emit(State.Login)
        }.collect {
            ui.value = it
        }
    }
}