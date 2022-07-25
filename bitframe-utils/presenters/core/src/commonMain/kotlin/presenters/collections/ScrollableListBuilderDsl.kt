package presenters.collections

import kotlinx.collections.interoperable.iListOf
import presenters.collections.internal.*
import viewmodel.ViewModelConfig
import kotlin.jvm.JvmSynthetic

@JvmSynthetic
fun <D> scrollableListOf(): ScrollableList<D> {
    val paginator = SinglePagePaginator<D>()
    val config = ViewModelConfig()
    val selector = SelectorImpl(paginator, config)
    val actionManager = ActionManagerImpl(selector) { iListOf() }
    return ScrollableListImpl(paginator, config, selector, actionManager)
}