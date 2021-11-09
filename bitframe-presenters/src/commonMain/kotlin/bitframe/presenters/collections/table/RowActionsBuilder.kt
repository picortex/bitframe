package bitframe.presenters.collections.table

import kotlinx.collections.interoperable.mutableListOf

class RowActionsBuilder<D> {
    internal val actions = mutableListOf<RowAction<D>>()

    fun action(name: String, handler: (Row<D>) -> Unit) {
        actions += RowAction(name, handler)
    }
}