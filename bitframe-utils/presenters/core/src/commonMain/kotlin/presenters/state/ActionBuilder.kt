package presenters.state

import kotlinx.collections.interoperable.mutableListOf

class ActionBuilder {
    internal val actions = mutableListOf<State.Action>()
    fun on(name: String, handler: () -> Unit): State.Action {
        val action = State.Action(name, handler)
        actions.add(action)
        return action
    }

    fun onRetry(handler: () -> Unit) = on("Retry", handler)

    fun onGoBack(handler: () -> Unit) = on("Go Back", handler)

    fun onOk(handler: () -> Unit) = on("Ok", handler)
}