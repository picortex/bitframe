@file:Suppress("NON_EXPORTABLE_TYPE", "WRONG_EXPORTED_DECLARATION")

package presenters.collections

import koncurrent.Later
import live.Live
import presenters.collections.internal.PaginatorImpl
import kotlin.js.JsExport

@JsExport
interface Paginator<out T> {
    val live: Live<PagedState<T>>
    val currentPageOrNull get() = live.value.currentPageOrNull
    var capacity: Int
    fun setPageCapacity(cap: Int)
    fun refresh(): Later<out Page<T>>
    fun next(): Later<out Page<T>>
    fun previous(): Later<out Page<T>>
    fun page(no: Int): Later<out Page<T>>
    fun first(): Later<out Page<T>>
    fun last(): Later<out Page<T>>

    companion object {
        val DEFAULT_CAPACITY = 10

        operator fun <T> invoke(
            capacity: Int = DEFAULT_CAPACITY,
            onPage: (no: Int, capacity: Int) -> Later<out Page<T>>
        ): Paginator<T> = PaginatorImpl(capacity, onPage)
    }
}