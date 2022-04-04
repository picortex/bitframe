@file:JsExport
@file:Suppress("MayBeConstant")

package pimonitor.core.events

import kotlin.js.JsExport
import kotlin.jvm.JvmField

object PimonitorEventTopics {
    @JvmField
    val SIGNED_UP = "pimonitor.authentication.signed.up"
}