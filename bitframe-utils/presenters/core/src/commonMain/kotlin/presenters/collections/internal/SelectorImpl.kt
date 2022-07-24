package presenters.collections.internal

import kotlinx.collections.interoperable.iListOf
import kotlinx.collections.interoperable.toInteroperableList
import presenters.collections.*
import viewmodel.ViewModelConfig

class SelectorImpl<T>(
    val paginator: Paginator<T>,
    val config: ViewModelConfig<*>,
    initial: SelectorState<T>? = null,
) : AbstractSelector<T>(paginator, config, initial) {

    override fun selectAllRowsInPage(page: Int?) {
        val p = page ?: return
        ui.value = SelectorState.Page(p)
    }

    override fun selectAllItemsInAllPages() {
        ui.value = SelectorState.AllSelected()
    }

    override fun unSelectAllItemsInAllPages() {
        ui.value = SelectorState.NoSelected
    }

    override fun unSelectAllRowsInPage(page: Int?) {
        ui.value = when (val state = ui.value) {
            is SelectorState.NoSelected -> SelectorState.NoSelected
            is SelectorState.Item -> if (state.page == page) SelectorState.NoSelected else state
            is SelectorState.Items -> SelectorState.Items(
                items = state.items.filter { page != it.page }.toInteroperableList()
            )

            is SelectorState.Page -> if (state.number == page) SelectorState.NoSelected else state
            is SelectorState.AllSelected -> SelectorState.NoSelected
        }
    }

    override fun isPageSelectedButPartially(page: Int?): Boolean = when (val state = ui.value) {
        is SelectorState.NoSelected -> false
        is SelectorState.Item -> false
        is SelectorState.Items -> state.items.any { it.page == page }
        is SelectorState.Page -> state.number == page
        is SelectorState.AllSelected -> true
    }

    override fun isPageSelectedWithNoExceptions(page: Int?): Boolean = when (val state = ui.value) {
        is SelectorState.NoSelected -> false
        is SelectorState.Item -> false
        is SelectorState.Items -> false
        is SelectorState.Page -> state.number == page && state.exceptions.isEmpty()
        is SelectorState.AllSelected -> true
    }

    override fun unSelectRowFromPage(row: Int, page: Int?) {
        ui.value = when (val state = ui.value) {
            is SelectorState.NoSelected -> SelectorState.NoSelected
            is SelectorState.Item -> if (state.page == page && state.number == row) SelectorState.NoSelected else state
            is SelectorState.Items -> SelectorState.Items(
                items = state.items.filter { it.page != page && it.number != row }.toInteroperableList()
            )

            is SelectorState.Page -> if (state.number == page) state.copy(
                exceptions = (state.exceptions.toMutableList() + row).toInteroperableList()
            ) else state

            is SelectorState.AllSelected -> state.copy(
                exceptions = (state.exceptions.toMutableList() + row).toInteroperableList()
            )
        }
    }

    override fun addRowSelection(row: Int, page: Int?) {
        val pageNo = page ?: return
        ui.value = when (val state = ui.value) {
            is SelectorState.NoSelected -> SelectorState.Item(row, pageNo)
            is SelectorState.Item -> SelectorState.Items(
                items = iListOf(state, SelectorState.Item(row, pageNo))
            )

            is SelectorState.Items -> SelectorState.Items(
                items = (state.items.toMutableList() + SelectorState.Item(row, pageNo)).toInteroperableList()
            )

            is SelectorState.Page -> SelectorState.Item(row, pageNo)
            is SelectorState.AllSelected -> SelectorState.Item(row, pageNo)
        }
    }

    override fun selectRow(row: Int, page: Int?) {
        val p = page ?: return
        ui.value = SelectorState.Item(row, p)
    }

    fun map(paginator: Paginator<T>): SelectorImpl<T> = SelectorImpl(
        paginator, config, ui.value
    )

    override fun isRowItemSelected(row: Int, page: Int?) = when (val state = ui.value) {
        is SelectorState.NoSelected -> false
        is SelectorState.Item -> state.number == row && state.page == page
        is SelectorState.Items -> state.items.any { it.number == row && it.page == page }
        is SelectorState.Page -> state.number == page
        is SelectorState.AllSelected -> true
    }
}