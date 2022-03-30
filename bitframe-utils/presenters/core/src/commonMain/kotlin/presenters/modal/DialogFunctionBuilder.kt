package presenters.modal

import kotlinx.collections.interoperable.listOf
import presenters.forms.Form
import presenters.modal.builders.ConfirmDialogActionsBuilder
import presenters.modal.builders.ConfirmDialogBuildingBlock
import presenters.forms.FormActionsBuilder
import presenters.forms.FormActionsBuildingBlock
import kotlin.experimental.ExperimentalTypeInference

@OptIn(ExperimentalTypeInference::class)
fun <F, P> formDialog(
    heading: String,
    details: String,
    fields: F,
    @BuilderInference initializer: FormActionsBuildingBlock<P>
): FormDialog<F, P> {
    val builder = FormActionsBuilder<P>().apply { initializer() }
    val submitAction = builder.submitAction
    return FormDialog(heading, details, fields, builder.actions, submitAction)
}

fun <F, P> dialog(form: Form<F, P>) = FormDialog(
    heading = form.heading,
    details = form.details,
    fields = form.fields,
    actions = listOf(form.cancel),
    submit = form.submit,
)

fun confirmDialog(
    heading: String,
    details: String,
    initializer: ConfirmDialogBuildingBlock
): ConfirmDialog {
    val builder = ConfirmDialogActionsBuilder().apply { initializer() }
    val confirm = builder.confirmAction
    return ConfirmDialog(heading, details, builder.actions, confirm)
}