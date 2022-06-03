@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor.client.signup

import bitframe.client.ReactUIScope
import bitframe.client.UIScopeConfig
import bitframe.core.Session
import events.EventCallback
import pimonitor.client.MonitorApi
import pimonitor.core.signup.SignUpResult
import useSubscriber
import viewmodel.asState
import pimonitor.client.signup.SignUpState as State

class SignUpReactScope internal constructor(
    override val config: UIScopeConfig<MonitorApi>
) : SignUpScope(config), ReactUIScope<State> {

    override val useScopeState = { viewModel.asState() }

    val useSignedUpEvent = { callback: EventCallback<SignUpResult> ->
        useSubscriber(config.service.events.onSignedUp(callback))
    }

    val useSignedInEvent = { callback: EventCallback<Session.SignedIn> ->
        useSubscriber(config.service.events.onSignedIn(callback))
    }
}