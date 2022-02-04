@file:JsExport
@file:Suppress("WRONG_EXPORTED_DECLARATION")

package bitframe.modal

import kotlin.js.JsExport
import kotlin.js.JsName

interface HasId {
    val uid: String

    @JsName("_ignore_")
    fun copy(id: String): HasId

    companion object {
        const val UNSET = ""
    }
}