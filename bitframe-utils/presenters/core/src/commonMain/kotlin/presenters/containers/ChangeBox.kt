@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package presenters.containers

import kotlinx.serialization.Serializable
import kotlin.js.JsExport

@Serializable
class ChangeBox<D>(
    val current: D,
    val previous: D,
    val details: String,
    val remark: ChangeRemark
)