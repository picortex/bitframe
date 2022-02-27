package presenters.modal

import kotlinx.collections.interoperable.mutableListOf

class DialogBuilder {
    internal val actions = mutableListOf<Action>()

    fun on(action: String, handler: () -> Unit) {
        actions.add(Action(action, handler))
    }

    fun onCancel(handler: () -> Unit) = on("Cancel", handler)

    fun onOk(handler: () -> Unit) = on("Ok", handler)

    fun onYes(handler: () -> Unit) = on("Yes", handler)

    fun onNo(handler: () -> Unit) = on("No", handler)

    fun onConfirm(handler: () -> Unit) = on("Confirm", handler)
}