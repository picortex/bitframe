@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package presenters.tabs

import kotlin.js.JsExport

open class Tab(
    val name: String,
    val onClick: ((Tab) -> Unit)
) {
    override fun equals(other: Any?) = other is Tab && other.name == name && other::class == this::class
}