@file:JsExport
@file:Suppress("WRONG_EXPORTED_DECLARATION")

package presenters.modal

import kotlin.js.JsExport

interface Dialog {
    val title: String
    val subTitle: String
}