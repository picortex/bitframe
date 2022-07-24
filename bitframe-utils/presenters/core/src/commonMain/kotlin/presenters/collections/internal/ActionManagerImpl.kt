package presenters.collections.internal

import kotlinx.collections.interoperable.List
import presenters.actions.SimpleAction
import presenters.collections.ActionManager
import presenters.collections.SelectorState

class ActionManagerImpl<T>(
    private val selector: SelectorImpl<T>,
    private val builder: (SelectorState<T>) -> List<SimpleAction>
) : ActionManager {
    override val actions: List<SimpleAction> get() = builder(selector.ui.value)
}