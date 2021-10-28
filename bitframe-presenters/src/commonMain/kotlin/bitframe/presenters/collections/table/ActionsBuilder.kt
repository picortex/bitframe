package bitframe.presenters.collections.table

class ActionsBuilder<D> {
    internal val actions = mutableListOf<Action<D>>()

    fun action(name: String, handler: (D) -> Unit) {
        actions += Action(name, handler)
    }
}