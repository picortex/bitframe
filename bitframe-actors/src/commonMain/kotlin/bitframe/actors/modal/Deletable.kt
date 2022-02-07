@file:JsExport
@file:Suppress("WRONG_EXPORTED_DECLARATION")

package bitframe.actors.modal

import kotlin.js.JsExport
import kotlin.js.JsName

interface Deletable {
    val deleted: Boolean

    @JsName("_ignore_deletableCopy")
    fun copy(deleted: Boolean): Deletable
}