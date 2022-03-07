@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package presenters.charts

import kotlinx.collections.interoperable.List
import kotlinx.collections.interoperable.listOf
import kotlinx.collections.interoperable.toInteroperableList
import kotlin.js.JsExport

data class BarChart<D : Number>(
    val title: String,
    val description: String,
    val entries: List<Entry<D>> = listOf(),
) {
    val labels
        get() = entries.map { it.label }.toInteroperableList()
    val data get() = entries.map { it.value }.toInteroperableList()

    data class Entry<D : Number>(
        val label: String,
        val value: D
    )
}