@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package presenters.containers

import kotlinx.serialization.Serializable
import kotlin.js.JsExport

@Serializable
data class GenericChangeBox<out T>(
    override val previous: T,
    override val current: T,
    override val details: String
) : ChangeBox<T>() {
    override val change: ChangeRemark<T> get() = ChangeRemark.Indeterminate
}