@file:Suppress("FunctionName", "NOTHING_TO_INLINE")

package presenters.collections

import koncurrent.Later
import koncurrent.SynchronousExecutor
import presenters.collections.internal.PaginatorImpl

inline fun <T> SinglePagePaginator(
    currentPage: Page<T> = Page()
): Paginator<T> = PaginatorImpl(capacity = currentPage.capacity) { _, _ ->
    Later.resolve(currentPage)
}

inline fun <T> SinglePagePaginator(
    items: Collection<T>
): Paginator<T> = SinglePagePaginator(Page(items))

fun <T> CollectionPaginator(
    collection: Collection<T>,
    capacity: Int = Paginator.DEFAULT_CAPACITY,
): Paginator<T> = Paginator(capacity) { no, cap ->
    Later(SynchronousExecutor) { resolve, reject ->
        try {
            val chunked = collection.chunked(cap)
            val page = if (no <= 0) {
                Page(chunked.last(), cap, chunked.size)
            } else {
                Page(chunked[no - 1], cap, no)
            }
            resolve(page)
        } catch (err: Throwable) {
            reject(err)
        }
    }
}