package bitframe.actor

import kotlin.js.JsExport

@JsExport
interface Savable {
    val uid: String
    val deleted: Boolean

    fun copySavable(uid: String, deleted: Boolean): Savable
}