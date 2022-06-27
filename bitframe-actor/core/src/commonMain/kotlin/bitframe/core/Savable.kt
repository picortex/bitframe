package bitframe.core

import kotlin.js.JsExport

@Deprecated("In favour of bitframe.Savable")
@JsExport
interface Savable {
    val uid: String
    val deleted: Boolean

    fun copySavable(uid: String, deleted: Boolean): Savable
}