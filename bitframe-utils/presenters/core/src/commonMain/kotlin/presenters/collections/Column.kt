@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package presenters.collections

import kotlinx.collections.interoperable.List
import kotlinx.collections.interoperable.emptyList
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import presenters.actions.GenericAction
import kotlin.js.JsExport

sealed class Column<in D> {
    abstract val name: String

    data class Select(
        override val name: String,
    ) : Column<Nothing>()

    data class Data<in D>(
        override val name: String,
        val accessor: (Row<D>) -> String = { "" }
    ) : Column<D>()

    data class Action<in D>(
        override val name: String,
        val actions: List<GenericAction<D>> = emptyList()
    ) : Column<D>()
}