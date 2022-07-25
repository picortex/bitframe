package presenters.collections

import presenters.collections.internal.ActionManagerImpl

fun <T> actionsOf(
    selector: Selector<T>,
    builder: ActionManagerBuilder<T>.() -> Unit
): ActionManager = ActionManagerImpl(selector, ActionManagerBuilder<T>().apply(builder))