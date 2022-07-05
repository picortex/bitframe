package presenters.forms

import presenters.actions.GenericPendingAction
import presenters.actions.SimpleAction
import presenters.forms.internal.FormImpl

typealias FormActionsBuildingBlock<T> = FormActionsBuilder<T>.() -> GenericPendingAction<T>

fun <F : Fields, P> Form(
    heading: String,
    details: String,
    fields: F,
    initializer: FormActionsBuildingBlock<P>
): Form<F, P> {
    val builtActions = FormActionsBuilder<P>().apply { initializer() }
    val cancel = builtActions.actions.firstOrNull {
        it.name.contentEquals("Cancel", ignoreCase = true)
    } ?: SimpleAction("Cancel") {}
    return FormImpl(
        heading = heading,
        details = details,
        fields = fields,
        cancel = cancel,
        submit = builtActions.submitAction
    )
}