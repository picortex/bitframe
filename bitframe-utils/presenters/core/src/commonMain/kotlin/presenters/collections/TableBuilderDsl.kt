package presenters.collections

import presenters.collections.internal.SelectorImpl
import presenters.collections.internal.SinglePagePaginator
import presenters.collections.internal.TableImpl
import viewmodel.ViewModelConfig
import kotlin.jvm.JvmSynthetic

class TableConfigImpl<D>(
    override val columns: Array<Column<D>>
) : TableConfig<D>, ViewModelConfig<Nothing?> by ViewModelConfig(api = null)

@JvmSynthetic
fun <D> tableOf(
    block: TableBuilder<D>.() -> Unit
): Table<D> {
    val builder = TableBuilder<D>().apply(block)
    val columns = builder.columns.toTypedArray()
    val paginator = SinglePagePaginator<D>()
    val config = TableConfigImpl(columns)
    val selector = SelectorImpl(paginator, config)
    return TableImpl(paginator, config, selector)
}