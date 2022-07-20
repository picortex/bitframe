package presenters.table.builders

import kotlinx.collections.interoperable.mutableListOf
import presenters.actions.ActionsBuilder
import presenters.table.Row
import presenters.table.RowAction

class RowActionsBuilder<D> : ActionsBuilder<RowAction<D>, (Row<D>) -> Unit>() {
    internal val actions = mutableListOf<RowAction<D>>()

    override fun on(name: String, handler: (Row<D>) -> Unit): RowAction<D> {
        val action = RowAction(name, handler)
        actions += action
        return action
    }
}