package bitframe.presenters.collections.table

import kotlinx.collections.interoperable.mutableListOf

class ActionsBuilder<D> {
    internal val actions = mutableListOf<Action<D>>()

    fun action(name: String, handler: (Row<D>) -> Unit) {
        actions += Action(name, handler)
    }
}