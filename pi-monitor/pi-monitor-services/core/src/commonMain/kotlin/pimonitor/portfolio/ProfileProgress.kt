@file:JsExport

package pimonitor.portfolio

import kotlinx.collections.interoperable.List
import presenters.fields.BooleanInputField
import kotlin.js.JsExport

data class ProfileProgress(
    val title: String,
    val items: List<BooleanInputField>
) {
    val progress get() = (items.filter { it.value == true }.size * 100) / items.size
}