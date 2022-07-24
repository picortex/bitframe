package presenters.collections.internal

import kotlinx.collections.interoperable.iListOf
import kotlinx.collections.interoperable.toInteroperableList
import presenters.collections.*
import viewmodel.ViewModel
import viewmodel.ViewModelConfig

class SelectorImpl<T>(
    val paginator: Paginator<T>,
    val config: ViewModelConfig<*>,
    initial: SelectorState<T>? = null,
) : ViewModel<SelectorState<T>>(config.of(initial ?: SelectorState.NoSelected)), Selector<T> {

    val currentLoadedPage get() = paginator.currentPageOrNull

    override fun selectAllItemsInTheCurrentPage() {
        TODO("Not yet implemented")
    }

    override fun selectAllItemsFromAllPages() {
        TODO("Not yet implemented")
    }

    override fun unSelectAllItemsInTheCurrentPage() {
        TODO("Not yet implemented")
    }

    override fun unSelectAllFromCurrentPage() {
        val pageNo = currentLoadedPage?.number
        ui.value = when (val state = ui.value) {
            SelectorState.NoSelected -> SelectorState.NoSelected
            is SelectorState.Single -> if (state.page == pageNo) SelectorState.NoSelected else state
            is SelectorState.Many -> SelectorState.Many(
                items = state.items.filter { pageNo == it.page }.toInteroperableList()
            )
        }
    }

    override fun deselect(item: Row<T>) {
        TODO("Not yet implemented")
    }

    override fun addItemToSelection(row: Int) {
        val r = currentLoadedPage?.items?.firstOrNull { row == it.number } ?: return
        addItemToSelection(r)
    }

    override fun addItemToSelection(row: Row<T>) {
        val pageNo = currentLoadedPage?.number ?: return
        ui.value = when (val state = ui.value) {
            is SelectorState.NoSelected -> SelectorState.Single(row, pageNo)
            is SelectorState.Single -> SelectorState.Many(
                items = iListOf(state, SelectorState.Single(row, pageNo))
            )

            is SelectorState.Many -> SelectorState.Many(
                items = (state.items.toMutableList() + SelectorState.Single(row, pageNo)).toInteroperableList()
            )
        }
    }

    override fun select(row: Row<T>) {
        val pageNo = currentLoadedPage?.number ?: return
        ui.value = SelectorState.Single(row, pageNo)
    }

    override fun select(row: Int) {
        val r = currentLoadedPage?.items?.firstOrNull { row == it.number } ?: return
        select(r)
    }

    fun isSelected(row: Row<T>, page: Int): Boolean = when (val state = ui.value) {
        is SelectorState.NoSelected -> false
        is SelectorState.Single -> state.number == row.number && state.page == page
        is SelectorState.Many -> state.items.any { it.number == row.number && it.page == page }
    }

    override fun isSelected(row: Row<T>): Boolean {
        val page = currentLoadedPage?.number ?: return false
        return isSelected(row, page)
    }

    fun map(paginator: Paginator<T>): SelectorImpl<T> = SelectorImpl(
        paginator, config, ui.value
    )

    override fun isSelected(row: Int): Boolean {
        val page = currentLoadedPage?.number ?: return false
        return isSelected(row, page)
    }

    override fun isSelected(row: Int, page: Int): Boolean {
        val r = currentLoadedPage?.items?.firstOrNull { row == it.number } ?: return false
        return isSelected(r)
    }
}