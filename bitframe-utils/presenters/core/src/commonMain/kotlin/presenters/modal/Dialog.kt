@file:Suppress("WRONG_EXPORTED_DECLARATION")

package presenters.modal

import kotlin.js.JsExport

@JsExport
interface Dialog {
    val title: String
    val subTitle: String
}