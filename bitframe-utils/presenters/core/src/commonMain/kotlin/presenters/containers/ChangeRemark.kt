@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package presenters.containers

import kotlinx.serialization.Serializable
import presenters.numerics.Percentage
import presenters.utils.NotSerializable
import kotlin.js.JsExport

sealed class ChangeRemark<out T> {

    data class Increase<out T>(
        val pct: Percentage,
        val value: T,
        val increased: Boolean,
    ) : ChangeRemark<T>()

    data class Decrease<out T>(
        val pct: Percentage,
        val value: T,
        val decreased: Boolean
    ) : ChangeRemark<T>()

    data class Fixed<out T>(
        val pct: Percentage = Percentage.ZERO,
        val at: T,
        val fixed: Boolean
    ) : ChangeRemark<T>()

    object Indeterminate : ChangeRemark<@Serializable(with = NotSerializable::class) Nothing>()
}