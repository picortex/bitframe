@file:Suppress("NON_EXPORTABLE_TYPE")

package events

import kotlin.js.JsExport
import kotlin.jvm.JvmOverloads

@JsExport
open class Event<out D> @JvmOverloads constructor(
    val data: D,
    val topic: String,
    val uid: String = UNSET
) {
    companion object {
        private const val UNSET = "<unset>"
    }
}