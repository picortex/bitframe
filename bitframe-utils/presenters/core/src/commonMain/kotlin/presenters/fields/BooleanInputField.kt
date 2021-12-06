@file:JsExport

package presenters.fields

import kotlinx.serialization.Serializable
import kotlin.js.JsExport

@Serializable
data class BooleanInputField(
    val label: String,
    var value: Boolean? = null
)