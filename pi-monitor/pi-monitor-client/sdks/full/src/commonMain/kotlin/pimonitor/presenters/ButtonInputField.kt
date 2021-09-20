@file:JsExport

package pimonitor.presenters

import kotlin.js.JsExport
import kotlin.js.JsName

data class ButtonInputField(
    val text: String,
) {
    @JsName("withHandler")
    constructor(text: String, handler: () -> Unit) : this(text) {
        this.handler = handler
    }

    var handler: (() -> Unit)? = null
}