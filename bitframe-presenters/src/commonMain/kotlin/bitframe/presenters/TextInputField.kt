@file:JsExport

package bitframe.presenters

import kotlinx.serialization.Serializable
import kotlin.js.JsExport

@Serializable
data class TextInputField(
    val label: String,
    val hint: String = label,
    var value: String? = null
)