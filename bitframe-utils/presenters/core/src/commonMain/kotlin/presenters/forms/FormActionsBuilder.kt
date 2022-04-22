package presenters.forms

import presenters.actions.GenericAction
import presenters.actions.SimpleActionsBuilder

class FormActionsBuilder<P> : SimpleActionsBuilder() {
    private var _submitAction: GenericAction<P>? = null
    internal val submitAction: GenericAction<P> get() = _submitAction ?: error("Submit action has not been initialize just yet")
    fun onSubmit(name: String = "Submit", handler: (P) -> Unit): GenericAction<P> {
        val action = GenericAction(name, handler)
        _submitAction = action
        return action
    }
}