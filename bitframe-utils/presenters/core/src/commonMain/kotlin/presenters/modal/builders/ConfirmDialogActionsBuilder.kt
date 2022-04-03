package presenters.modal.builders

import presenters.actions.SimpleAction
import presenters.actions.SimpleActionsBuilder

class ConfirmDialogActionsBuilder : SimpleActionsBuilder() {
    private var _confirmAction: SimpleAction? = null
    internal val confirmAction: SimpleAction get() = _confirmAction ?: error("Confirm Action has not yet been initialized")
    fun onConfirm(name: String = "Confirm", handler: () -> Unit): SimpleAction {
        val action = SimpleAction(name, handler)
        _confirmAction = action
        return action
    }
}