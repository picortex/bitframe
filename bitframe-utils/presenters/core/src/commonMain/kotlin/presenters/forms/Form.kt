@file:Suppress("NON_EXPORTABLE_TYPE")

package presenters.forms

import presenters.actions.GenericAction
import presenters.actions.SimpleAction
import kotlin.js.JsExport

@JsExport
interface Form<out F, in P> {
    val heading: String
    val details: String
    val fields: F
    val cancel: SimpleAction
    val submit: GenericAction<P>
}