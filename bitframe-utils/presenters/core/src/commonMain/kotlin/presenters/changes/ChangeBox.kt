@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package presenters.changes

import kotlinx.serialization.Serializable
import kotlin.js.JsExport

@Serializable
sealed class ChangeBox<out D> {
    abstract val previous: D
    abstract val current: D
    abstract val details: String
    abstract val change: ChangeRemark<D>
    abstract val feeling: ChangeFeeling
}