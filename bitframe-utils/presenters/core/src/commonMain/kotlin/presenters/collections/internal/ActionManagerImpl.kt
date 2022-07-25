package presenters.collections.internal

import kotlinx.collections.interoperable.List
import kotlinx.collections.interoperable.toInteroperableList
import presenters.actions.SimpleAction
import presenters.collections.ActionManager
import presenters.collections.ActionManagerBuilder
import presenters.collections.Selector
import presenters.collections.SelectorState

class ActionManagerImpl<T>(
    private val selector: Selector<T>,
    private val builder: ActionManagerBuilder<T>
) : ActionManager {
    override val actions: List<SimpleAction> get() = builder.buildActions(selector.selected)
}