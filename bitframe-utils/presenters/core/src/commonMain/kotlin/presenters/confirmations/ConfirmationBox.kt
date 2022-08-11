@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE", "WRONG_EXPORTED_DECLARATION")

package presenters.confirmations

import presenters.actions.MutableSimpleAction
import kotlin.js.JsExport

interface ConfirmationBox {
    val heading: String
    val details: String

    val cancelAction: MutableSimpleAction
    fun confirm()
}