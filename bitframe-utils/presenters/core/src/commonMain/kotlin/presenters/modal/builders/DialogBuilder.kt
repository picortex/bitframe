package presenters.modal.builders

import kotlinx.collections.interoperable.mutableListOf
import presenters.modal.DialogAction

abstract class DialogBuilder {
    val actions = mutableListOf<DialogAction>()

    fun on(action: String, handler: () -> Unit) {
        actions.add(DialogAction(action, handler))
    }

    fun onCancel(handler: () -> Unit) = on("Cancel", handler)

    fun onOk(handler: () -> Unit) = on("Ok", handler)

    fun onYes(handler: () -> Unit) = on("Yes", handler)

    fun onNo(handler: () -> Unit) = on("No", handler)
}