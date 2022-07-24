@file:Suppress("NON_EXPORTABLE_TYPE", "WRONG_EXPORTED_DECLARATION")

package presenters.collections

import presenters.collections.internal.SelectorImpl
import viewmodel.ViewModelConfig
import kotlin.js.JsExport
import kotlin.js.JsName

@JsExport
interface Selector<in T> {
    // ---------------------------------Selections--------------------------
    fun selectAllItemsInTheCurrentPage()

    fun selectAllItemsInPage(page: Int)

    fun selectAllItemsInAllPages()

    /**
     * Marks the item at row Number [row], in page [page] as selected
     * If there were other rows in the selected buffer, they will all be removed
     */
    @JsName("selectRowInPage")
    fun select(row: Int, page: Int)

    /**
     * Marks the item at row Number [row] in the current page
     * If there were other rows in the selected buffer, they will all be removed
     */
    @JsName("selectRow")
    fun select(row: Int)

    // ---------------------------------Selection Adders --------------------------

    /**
     * Marks the [row] in page [page] as selected
     * If there were other items in the selected buffer, this [row] will be appended to the buffer
     */
    @JsName("addSelectionOfRowInPage")
    fun addSelection(row: Int, page: Int)

    /**
     * Marks the [row] as selected
     * If there were other items in the selected buffer, this [row] will be appended to the buffer
     */
    @JsName("addSelectionOfRowNumber")
    fun addSelection(row: Int)

    // ---------------------------------Selection Toggles --------------------------

    @JsName("toggleSelectionOfRowInPage")
    fun toggleSelection(row: Int, page: Int)

    @JsName("toggleSelectionOfRow")
    fun toggleSelection(row: Int)

    // ---------------------------------Selection Checks--------------------------

    fun isPageSelectedWholly(page: Int): Boolean

    fun isPageSelectedPartially(page: Int): Boolean

    fun isCurrentPageSelectedWholly(): Boolean

    fun isCurrentPageSelectedPartially(): Boolean

    fun isRowSelected(row: Int): Boolean

    @JsName("isRowSelectedOnPage")
    fun isRowSelected(row: Int, page: Int): Boolean

    // ---------------------------------UnSelections--------------------------
    fun unSelectAllItemsInAllPages()

    fun unSelectAllItemsInTheCurrentPage()

    fun unSelectAllItemsInPage(page: Int)

    /**
     * Unselects the item from row number [row] in the current page and effectively removes it from the selected buffer
     */
    @JsName("unSelectRowNumber")
    fun unSelect(row: Int)

    /**
     * Unselects the item from row number [row] in page [page] and effectively removes it from the selected buffer
     */
    @JsName("unSelectItemFromPage")
    fun unSelect(row: Int, page: Int)

    companion object {
        operator fun <T> invoke(
            paginator: Paginator<T>,
            config: ViewModelConfig<*> = ViewModelConfig()
        ): Selector<T> = SelectorImpl(paginator, config)
    }
}