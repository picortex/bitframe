@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package presenters.modal

import presenters.modal.builders.ConfirmDialogActionsBuilder
import presenters.modal.builders.ConfirmDialogBuildingBlock
import presenters.modal.builders.FormDialogActionsBuilder
import presenters.modal.builders.FormDialogBuildingBlock
import kotlin.experimental.ExperimentalTypeInference
import kotlin.js.JsExport

@OptIn(ExperimentalTypeInference::class)
fun <F, P> formDialog(
    heading: String,
    details: String,
    fields: F,
    @BuilderInference initializer: FormDialogBuildingBlock<P>
): Dialog.Form<F, P> {
    val builder = FormDialogActionsBuilder<P>().apply { initializer() }
    val submitAction = builder.submitAction ?: error("Submit action is missing in dialog $heading")
    return Dialog.Form(heading, details, fields, builder.actions, submitAction)
}

fun confirmDialog(
    heading: String,
    details: String,
    initializer: ConfirmDialogBuildingBlock
): Dialog.Confirm {
    val builder = ConfirmDialogActionsBuilder().apply { initializer() }
    val confirm = builder.confirmAction ?: error("Confirm action is not set in dialog $heading")
    return Dialog.Confirm(heading, details, builder.actions, confirm)
}