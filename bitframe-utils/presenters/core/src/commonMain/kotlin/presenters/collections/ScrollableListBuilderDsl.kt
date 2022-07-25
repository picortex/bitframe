package presenters.collections

import presenters.collections.internal.ScrollableListImpl
import viewmodel.ViewModelConfig
import kotlin.jvm.JvmSynthetic

@JvmSynthetic
fun <T> scrollableListOf(
    paginator: Paginator<T>,
    selector: Selector<T>,
    actionManager: ActionManager,
    config: ViewModelConfig<*>
): ScrollableList<T> = ScrollableListImpl(paginator, selector, actionManager, config)