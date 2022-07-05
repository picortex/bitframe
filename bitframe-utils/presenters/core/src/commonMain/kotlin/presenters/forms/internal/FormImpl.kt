package presenters.forms.internal

import presenters.actions.GenericPendingAction
import presenters.actions.SimpleAction
import presenters.forms.Fields
import presenters.forms.Form

class FormImpl<F : Fields, P>(
    override val heading: String,
    override val details: String,
    override val fields: F,
    override val cancel: SimpleAction,
    override val submit: GenericPendingAction<P>
) : Form<F, P> {

    override fun validate() = fields.validate()
    override fun equals(other: Any?): Boolean = other is Form<*, *> && other.heading == heading
    override fun hashCode(): Int = heading.hashCode()
    override fun toString() = "Form($heading)"
}