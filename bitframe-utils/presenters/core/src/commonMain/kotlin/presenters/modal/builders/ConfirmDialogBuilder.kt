package presenters.modal.builders

import presenters.modal.ConfirmAction

class ConfirmDialogBuilder : DialogBuilder() {
    private var _confirmAction: ConfirmAction? = null
    internal val confirmAction: ConfirmAction get() = _confirmAction ?: error("Confirm Action has not yet been initialized")
    fun onConfirm(handler: () -> Unit): ConfirmAction {
        val action = ConfirmAction("Submit", handler)
        _confirmAction = action
        return action
    }
}