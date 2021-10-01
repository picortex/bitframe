@file:JsExport

package bitframe.presenters.fields

import kotlinx.serialization.Serializable
import kotlin.js.JsExport
import kotlin.js.JsName

@Serializable
data class ButtonInputField(val text: String) {
    @JsName("withHandler")
    constructor(text: String, handler: () -> Unit) : this(text) {
        this.handler = handler
    }

    var handler: (() -> Unit)? = null
}