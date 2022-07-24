@file:Suppress("NON_EXPORTABLE_TYPE", "WRONG_EXPORTED_DECLARATION")

package presenters.collections

import presenters.collections.internal.SelectorImpl
import viewmodel.ViewModelConfig
import kotlin.js.JsExport
import kotlin.js.JsName

@JsExport
interface Selector<in T> {
    fun selectAllItemsInTheCurrentPage()

    fun selectAllItemsFromAllPages()

    fun unSelectAllItemsInTheCurrentPage()

    fun unSelectAllFromCurrentPage()

    /**
     * Marks the item at row Number [row]
     * If there were other rows in the selected buffer, they will all be removed
     */
    @JsName("selectRowNumber")
    fun select(row: Int)

    /**
     * Marks the [row] as selected
     * If there were other rows in the selected buffer, they will all be removed
     */
    @JsName("selectRow")
    fun select(row: Row<T>)

    /**
     * Returns true an instance of [row] has been selected
     */
    @JsName("isItemSelected")
    fun isSelected(row: Row<T>): Boolean

    @JsName("isRowSelected")
    fun isSelected(row: Int): Boolean

    @JsName("isRowSelectedOnPage")
    fun isSelected(row: Int, page: Int): Boolean

    /**
     * Marks the [row] as selected
     * If there were other items in the selected buffer, this [row] will be appended to the buffer
     */
    @JsName("addRowNumberToSelection")
    fun addItemToSelection(row: Int)

    /**
     * Marks the [row] as selected
     * If there were other items in the selected buffer, this [row] will be appended to the buffer
     */
    @JsName("addRowToSelection")
    fun addItemToSelection(row: Row<T>)

    /**
     * Deselects the [item] and effectively removes it from the selected buffer
     */
    fun deselect(item: Row<T>)

    companion object {
        operator fun <T> invoke(
            paginator: Paginator<T>,
            config: ViewModelConfig<*> = ViewModelConfig()
        ): Selector<T> = SelectorImpl(paginator, config)
    }
}