package presenters.forms

import presenters.actions.GenericAction
import presenters.actions.SimpleAction

internal class FormImpl<F, P>(
    override val heading: String,
    override val details: String,
    override val fields: F,
    override val cancel: SimpleAction,
    override val submit: GenericAction<P>
) : Form<F, P> {
    override fun equals(other: Any?): Boolean = other is Form<*, *> && other.heading == heading
    override fun hashCode(): Int = heading.hashCode()
    override fun toString() = "Form($heading)"
}