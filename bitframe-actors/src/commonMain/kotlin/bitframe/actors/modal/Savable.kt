@file:JsExport
@file:Suppress("WRONG_EXPORTED_DECLARATION")

package bitframe.actors.modal

import kotlin.js.JsExport
import kotlin.js.JsName

interface Savable : HasId, Deletable {
    override fun copyDeleted(deleted: Boolean) = copySavable(id = uid, deleted = deleted)

    override fun copyId(id: String) = copySavable(id = id, deleted = deleted)

    @JsName("_ignore_copySavable")
    fun copySavable(id: String, deleted: Boolean): Savable
}