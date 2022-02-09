@file:JsExport
@file:Suppress("WRONG_EXPORTED_DECLARATION")

package bitframe.actors.modal

import kotlin.js.JsExport
import kotlin.js.JsName

interface Deletable {
    val deleted: Boolean

    @JsName("_ignore_copyDeleted")
    fun copyDeleted(deleted: Boolean): Deletable
}