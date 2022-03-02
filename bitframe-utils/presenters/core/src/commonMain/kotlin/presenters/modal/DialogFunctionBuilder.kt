@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package presenters.modal

import presenters.modal.builders.ConfirmDialogBuilder
import presenters.modal.builders.FormDialogBuilder
import kotlin.js.JsExport

fun <F, P> formDialog(
    heading: String,
    details: String,
    fields: F,
    initializer: FormDialogBuilder<P>.() -> SubmitAction<P>
): Dialog.Form<F, P> {
    val builder = FormDialogBuilder<P>().apply { initializer() }
    val submitAction = builder.submitAction ?: error("Submit action is missing in dialog $heading")
    return Dialog.Form(heading, details, fields, builder.actions, submitAction)
}

fun confirmDialog(
    heading: String,
    details: String,
    initializer: ConfirmDialogBuilder.() -> ConfirmAction
): Dialog.Confirm {
    val builder = ConfirmDialogBuilder().apply { initializer() }
    val confirm = builder.confirmAction ?: error("Confirm action is not set in dialog $heading")
    return Dialog.Confirm(heading, details, builder.actions, confirm)
}