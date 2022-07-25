@file:JsExport

package presenters.collections

import kotlinx.collections.interoperable.List
import kotlin.js.JsExport

@Suppress("NON_EXPORTABLE_TYPE")
sealed class Selected<out T> {

    object None : Selected<Nothing>()

    data class Item<out T>(
        val value: T
    ) : Selected<T>()

    data class Items<out T>(
        val values: List<T>
    ) : Selected<T>()

    data class Global<out T>(
        val exceptions: List<T>
    ) : Selected<T>()
}