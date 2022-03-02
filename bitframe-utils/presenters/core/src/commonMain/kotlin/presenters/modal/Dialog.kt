@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package presenters.modal

import kotlinx.collections.interoperable.List
import kotlin.js.JsExport

sealed class Dialog {
    abstract val heading: String
    abstract val details: String
    abstract val actions: List<DialogAction>

    data class Form<out F, in P>(
        override val heading: String,
        override val details: String,
        val fields: F,
        override val actions: List<DialogAction>,
        val submit: SubmitAction<P>
    ) : Dialog()

    data class Confirm(
        override val heading: String,
        override val details: String,
        override val actions: List<DialogAction>,
        val confirm: ConfirmAction
    ) : Dialog()
}