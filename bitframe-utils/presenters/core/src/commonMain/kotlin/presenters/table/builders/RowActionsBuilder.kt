package presenters.table.builders

import kotlinx.collections.interoperable.mutableListOf
import presenters.table.Row
import presenters.table.RowAction

class RowActionsBuilder<D> {
    internal val actions = mutableListOf<RowAction<D>>()

    fun action(name: String, handler: (Row<D>) -> Unit) {
        actions += RowAction(name, handler)
    }
}