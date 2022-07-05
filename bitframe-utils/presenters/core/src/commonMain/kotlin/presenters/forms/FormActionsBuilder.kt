package presenters.forms

import koncurrent.Later
import presenters.actions.GenericAction
import presenters.actions.GenericPendingAction
import presenters.actions.SimpleActionsBuilder

class FormActionsBuilder<P> : SimpleActionsBuilder() {
    private var _submitAction: GenericPendingAction<P>? = null
    val submitAction: GenericPendingAction<P> get() = _submitAction ?: error("Submit action has not been initialize just yet")
    fun onSubmit(name: String = "Submit", handler: (P) -> Later<out Any?>): GenericPendingAction<P> {
        val action = GenericPendingAction(name, handler)
        _submitAction = action
        return action
    }
}