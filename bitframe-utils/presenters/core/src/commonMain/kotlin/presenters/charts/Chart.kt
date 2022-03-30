@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package presenters.charts

import kotlinx.collections.interoperable.List
import kotlinx.serialization.Serializable
import kotlin.js.JsExport

@Serializable
data class Chart<out D>(
    val title: String,
    val description: String,
    val labels: List<String>,
    val datasets: List<DataSet<D>>,
) {
    @Serializable
    data class DataSet<out D>(
        val name: String,
        val values: List<D>
    )
}