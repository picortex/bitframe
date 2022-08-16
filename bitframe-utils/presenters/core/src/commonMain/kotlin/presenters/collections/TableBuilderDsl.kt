package presenters.collections

import kotlinx.collections.interoperable.List
import presenters.collections.internal.TableImpl
import viewmodel.ViewModelConfig
import kotlin.jvm.JvmSynthetic

@JvmSynthetic
fun <T> tableOf(
    paginator: PaginationManager<T>,
    selector: SelectionManager<T>,
    actionsManager: ActionsManager<T>,
    columns: List<Column<T>>,
    config: ViewModelConfig = ViewModelConfig()
): Table<T> = TableImpl(paginator, selector, actionsManager, columns, config)