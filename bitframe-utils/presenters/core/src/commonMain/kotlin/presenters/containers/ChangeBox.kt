@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package presenters.containers

import kotlinx.serialization.Serializable
import kotlin.js.JsExport

@Serializable
data class ChangeBox<out D>(
    val previous: D,
    val current: D,
    val details: String,
    val change: ChangeRemark<D>
)