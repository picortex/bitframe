@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package presenters.tabs

import kotlinx.collections.interoperable.List
import kotlinx.collections.interoperable.emptyList
import kotlin.js.JsExport

data class Tabs(
    val tabs: List<Tab> = emptyList(),
    val active: Tab? = tabs.firstOrNull()
)