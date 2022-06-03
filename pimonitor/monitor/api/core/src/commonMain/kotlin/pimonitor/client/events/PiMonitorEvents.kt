@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor.client.events

import bitframe.client.BitframeEvents
import events.EventBus
import events.EventCallback
import pimonitor.core.events.PimonitorEventTopics
import pimonitor.core.signup.SignUpResult
import kotlin.js.JsExport

class PiMonitorEvents(private val bus: EventBus) : BitframeEvents(bus) {
    val onSignedUp = { callback: EventCallback<SignUpResult> ->
        bus.subscribe(PimonitorEventTopics.SIGNED_UP, callback)
    }
}