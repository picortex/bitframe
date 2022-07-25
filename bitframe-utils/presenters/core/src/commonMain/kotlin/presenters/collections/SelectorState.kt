@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package presenters.collections

import kotlinx.collections.interoperable.List
import kotlinx.collections.interoperable.iListOf
import kotlin.js.JsExport

sealed class SelectorState {
    object NoSelected : SelectorState()

    data class Item(
        val number: Int,
        val page: Int
    ) : SelectorState()

    data class Items(
        val items: List<Item>
    ) : SelectorState()

    data class AllSelected(
        val exceptions: List<Item> = iListOf()
    ) : SelectorState()
}