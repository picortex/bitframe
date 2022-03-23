package presenters.modal

import presenters.modal.builders.ConfirmDialogActionsBuilder
import presenters.modal.builders.ConfirmDialogBuildingBlock
import presenters.modal.builders.FormDialogActionsBuilder
import presenters.modal.builders.FormDialogBuildingBlock
import kotlin.experimental.ExperimentalTypeInference

@OptIn(ExperimentalTypeInference::class)
fun <F, P> formDialog(
    heading: String,
    details: String,
    fields: F,
    @BuilderInference initializer: FormDialogBuildingBlock<P>
): Dialog.Form<F, P> {
    val builder = FormDialogActionsBuilder<P>().apply { initializer() }
    val submitAction = builder.submitAction
    return Dialog.Form(heading, details, fields, builder.actions, submitAction)
}

fun confirmDialog(
    heading: String,
    details: String,
    initializer: ConfirmDialogBuildingBlock
): Dialog.Confirm {
    val builder = ConfirmDialogActionsBuilder().apply { initializer() }
    val confirm = builder.confirmAction
    return Dialog.Confirm(heading, details, builder.actions, confirm)
}