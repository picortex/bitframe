@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package presenters.collections

import kotlinx.collections.interoperable.List
import kotlinx.collections.interoperable.iListOf
import kotlin.js.JsExport

sealed class SelectorState<out T> {
    object NoSelected : SelectorState<Nothing>()

    data class Item<out T>(
        val number: Int,
        val page: Int
    ) : SelectorState<T>()

    data class Items<out T>(
        val items: List<Item<T>>
    ) : SelectorState<T>()

    data class Page(
        val number: Int,
        val exceptions: List<Int> = iListOf()
    ) : SelectorState<Nothing>()

    data class AllSelected(
        val exceptions: List<Int> = iListOf()
    ) : SelectorState<Nothing>()
}
