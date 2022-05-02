package presenters.cases

import presenters.modal.Dialog

fun <C, D> GenericState<C, D>.copy(dialog: Dialog<*, *>?): GenericState<C, D> = when (this) {
    is GenericState.Content -> copy(dialog = dialog)
    else -> this
}

fun <C, D> GenericState<C, D>.copy(
    data: D,
    context: C? = this.context,
): GenericState<C, D> = when (this) {
    is GenericState.Content -> if (context != null) {
        copy(data = data, dialog = this.dialog, context = context)
    } else {
        copy(data = data, dialog = this.dialog)
    }
    else -> if (context != null) {
        GenericState.Content(data, dialog = null, context = context)
    } else error("Can't set data while context is missing")
}
