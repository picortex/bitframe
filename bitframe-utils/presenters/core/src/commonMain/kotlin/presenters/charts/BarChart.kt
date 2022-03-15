@file:JsExport @file:Suppress("NON_EXPORTABLE_TYPE")

package presenters.charts

import kotlinx.collections.interoperable.List
import kotlinx.collections.interoperable.listOf
import kotlinx.collections.interoperable.toInteroperableList
import kotlinx.serialization.Serializable
import kotlin.js.JsExport

@Serializable
data class BarChart<D : Number>(
    override val title: String,
    override val description: String,
    val entries: List<Entry<D>> = listOf(),
) : Chart {
    val labels
        get() = entries.map { it.label }.toInteroperableList()
    val data get() = entries.map { it.value }.toInteroperableList()

    @Serializable
    data class Entry<D : Number>(
        val label: String, val value: D
    )
}