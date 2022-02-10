@file:JsExport
@file:Suppress("WRONG_EXPORTED_DECLARATION")

package bitframe.actors.modal

import kotlin.js.JsExport
import kotlin.js.JsName

interface HasId {
    val uid: String

    @JsName("_ignore_copyId")
    fun copyId(uid: String): HasId

    companion object {
        const val UNSET = ""
    }
}