@file:OptIn(ExperimentalTypeInference::class)

package presenters.forms

import presenters.actions.SimpleAction
import kotlin.experimental.ExperimentalTypeInference

fun <F, P> Form(
    heading: String,
    details: String,
    fields: F,
    @BuilderInference initializer: FormActionsBuildingBlock<P>
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