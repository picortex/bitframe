@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor.core.portfolio

import kotlinx.collections.interoperable.List
import kotlinx.collections.interoperable.emptyList
import kotlinx.serialization.Serializable
import presenters.fields.BooleanInputField
import kotlin.js.JsExport

@Serializable
data class ProfileProgress(
    val title: String = "Complete your profile",
    val items: List<BooleanInputField> = emptyList()
) {
    val progress get() = (items.filter { it.value == true }.size * 100) / items.size
}