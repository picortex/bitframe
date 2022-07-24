@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package presenters.collections

import kotlinx.collections.interoperable.List
import kotlin.js.JsExport

sealed class SelectorState<out T> {
    object NoSelected : SelectorState<Nothing>()

    data class Single<out T>(
        val row: Row<T>,
        val page: Int
    ) : SelectorState<T>(), Row<T> by row

    data class Many<out T>(
        val items: List<Single<T>>
    ) : SelectorState<T>()
}
