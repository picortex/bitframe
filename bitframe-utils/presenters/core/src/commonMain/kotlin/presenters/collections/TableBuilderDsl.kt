package presenters.collections

import presenters.collections.internal.TableImpl
import viewmodel.ViewModelConfig
import kotlin.jvm.JvmSynthetic

@JvmSynthetic
fun <T> tableOf(
    paginator: Paginator<T>,
    selector: Selector<T>,
    actionManager: ActionManager,
    columns: Array<Column<T>>,
    config: ViewModelConfig<*>
): Table<T> = TableImpl(paginator, selector, actionManager, columns, config)