@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package bitframe.client

import bitframe.core.Session
import bitframe.core.events.AuthEventTopics
import events.EventBus
import events.EventCallback
import kotlin.js.JsExport

open class BitframeEvents(private val bus: EventBus) {
    val onSpaceSwitched = { callback: EventCallback<Session.SignedIn> ->
        bus.subscribe(AuthEventTopics.SPACE_SWITCHED, callback)
    }

    val onUserSignedIn = { callback: EventCallback<Session.SignedIn> ->
        bus.subscribe(AuthEventTopics.SIGNED_IN, callback)
    }

    val onUserSignedOut = { callback: EventCallback<Session.SignedIn> ->
        bus.subscribe(AuthEventTopics.SIGNED_OUT, callback)
    }
}