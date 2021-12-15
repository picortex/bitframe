@file:JsExport

package events

import kotlin.js.JsExport

open class Event<out D>(
    val id: String,
    val data: D
)