@file:Suppress("NON_EXPORTABLE_TYPE")

package presenters.collections

import kotlin.js.JsExport

@JsExport
interface Selectable<in T> {
    fun selectAll()

    fun unSelectAll()

    fun select(item: T)

    fun deselect(item: T)
}