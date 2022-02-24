@file:JsExport

package presenters.fields

import kotlinx.serialization.Serializable
import kotlin.js.JsExport

@Serializable
data class BooleanInputField(
    override val name: String,
    val label: String = name,
    var value: Boolean? = null
) : InputField