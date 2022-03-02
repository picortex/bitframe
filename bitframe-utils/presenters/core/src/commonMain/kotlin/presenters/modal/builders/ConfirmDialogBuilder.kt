package presenters.modal.builders

import presenters.modal.ConfirmAction

class ConfirmDialogBuilder : DialogBuilder() {
    internal var confirmAction: ConfirmAction? = null
    fun onConfirm(handler: () -> Unit): ConfirmAction {
        val action = ConfirmAction("Submit", handler)
        confirmAction = action
        return action
    }
}