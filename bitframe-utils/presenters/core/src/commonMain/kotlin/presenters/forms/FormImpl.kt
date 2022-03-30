package presenters.forms

import presenters.actions.GenericAction
import presenters.actions.SimpleAction

internal data class FormImpl<F, P>(
    override val heading: String,
    override val details: String,
    override val fields: F,
    override val cancel: SimpleAction,
    override val submit: GenericAction<P>
) : Form<F, P>