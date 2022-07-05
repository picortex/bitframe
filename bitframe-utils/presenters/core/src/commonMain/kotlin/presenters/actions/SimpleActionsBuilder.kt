package presenters.actions

import kotlinx.collections.interoperable.mutableListOf

open class SimpleActionsBuilder {
    val actions = mutableListOf<SimpleAction>()
    fun on(name: String, handler: () -> Unit): SimpleAction {
        val action = SimpleAction(name, handler)
        actions.add(action)
        return action
    }

    fun onCancel(handler: () -> Unit) = on("Cancel", handler)

    fun onOk(handler: () -> Unit) = on("Ok", handler)

    fun onYes(handler: () -> Unit) = on("Yes", handler)

    fun onNo(handler: () -> Unit) = on("No", handler)

    fun onRetry(handler: () -> Unit) = on("Retry", handler)

    fun onGoBack(handler: () -> Unit) = on("Go Back", handler)
}