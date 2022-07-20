package presenters.modal.builders

import koncurrent.Later
import presenters.actions.SimpleActionsBuilder
import presenters.actions.SimplePendingAction

class ConfirmDialogActionsBuilder : SimpleActionsBuilder() {
    private var _confirmAction: SimplePendingAction? = null
    internal val confirmAction: SimplePendingAction get() = _confirmAction ?: error("Confirm Action has not yet been initialized")
    fun onConfirm(name: String = "Confirm", handler: () -> Later<out Unit>): SimplePendingAction {
        val action = SimplePendingAction(name, handler)
        _confirmAction = action
        return action
    }
}