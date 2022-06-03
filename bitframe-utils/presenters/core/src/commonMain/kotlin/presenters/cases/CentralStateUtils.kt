package presenters.cases

import presenters.actions.SimpleActionsBuilder
import presenters.forms.Form
import presenters.modal.Dialog
import presenters.table.Table
import presenters.modal.dialog as Dialog

fun <C, D> CentralState<C, D>.table(
    table: Table<D>,
    context: C? = this.context,
    dialog: Dialog<*, *>? = null
) = CentralState(
    emphasis = dialog?.let { Emphasis.Modal(it) } ?: Emphasis.None,
    table = table,
    context = context
)

private fun <C, D> CentralState<C, D>.emphasis(emphasis: Emphasis, context: C?) = CentralState(
    emphasis = emphasis,
    table = table,
    context = context ?: this.context
)

fun <C, D> CentralState<C, D>.loading(message: String, context: C? = null) = emphasis(
    Emphasis.Loading(message), context
)

fun <C, D> CentralState<C, D>.success(
    message: String,
    builder: (SimpleActionsBuilder.() -> Unit)? = null
) = emphasis(Emphasis.Success(message, builder), null)

fun <C, D> CentralState<C, D>.failure(
    cause: Throwable? = null,
    message: String = cause?.message ?: Failure.DEFAULT_MESSAGE,
    builder: (SimpleActionsBuilder.() -> Unit)? = null
) = emphasis(Emphasis.Failure(cause, message, builder = builder), null)

fun <C, D> CentralState<C, D>.dialog(
    dialog: Dialog<*, *>
) = emphasis(Emphasis.Modal(dialog), null)

fun <C, D> CentralState<C, D>.dialog(
    form: Form<*, *>
) = emphasis(Emphasis.Modal(Dialog(form)), null)

fun <C, D> CentralState<C, D>.withoutEmphasis() = emphasis(
    Emphasis.None, null
)