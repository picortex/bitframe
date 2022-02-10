@file:JsExport
@file:Suppress("WRONG_EXPORTED_DECLARATION")

package bitframe.actors.modal

import kotlin.js.JsExport
import kotlin.js.JsName

interface Savable : HasId, Deletable {
    override fun copyDeleted(deleted: Boolean) = copySavable(uid = uid, deleted = deleted)

    override fun copyId(uid: String) = copySavable(uid = uid, deleted = deleted)

    @JsName("_ignore_copySavable")
    fun copySavable(uid: String, deleted: Boolean): Savable
}