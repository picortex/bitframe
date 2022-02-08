@file:JsExport
@file:Suppress("WRONG_EXPORTED_DECLARATION")

package bitframe.actors.modal

import kotlin.js.JsExport
import kotlin.js.JsName

interface Savable : HasId, Deletable {
    override fun copy(deleted: Boolean) = copy(id = uid, deleted = deleted)

    override fun copy(id: String) = copy(id = id, deleted = deleted)

    @JsName("_ignore_savableCopy")
    fun copy(id: String, deleted: Boolean): Savable
}