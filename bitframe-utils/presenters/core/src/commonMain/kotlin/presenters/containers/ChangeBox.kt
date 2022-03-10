@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package presenters.containers

import kotlinx.serialization.Serializable
import kotlin.js.JsExport

@Serializable
data class ChangeBox<D>(
    val precursor: D,
    val successor: D,
    val details: String,
    val remark: ChangeRemark
)