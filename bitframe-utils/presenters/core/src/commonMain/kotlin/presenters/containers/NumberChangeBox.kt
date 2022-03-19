@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package presenters.containers

import kotlinx.serialization.Serializable
import kotlin.js.JsExport

@Serializable
data class NumberChangeBox<N : Number>(
    override val previous: N,
    override val current: N,
    override val details: String,
) : ChangeBox<N>() {
    override val change by lazy { changeRemarkOf(previous, current) as ChangeRemark<N> }
}