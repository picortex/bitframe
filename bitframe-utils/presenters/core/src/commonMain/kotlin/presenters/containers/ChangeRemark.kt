@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package presenters.containers

import kotlinx.serialization.Serializable
import presenters.numerics.Percentage
import kotlin.js.JsExport

@Serializable
sealed class ChangeRemark<out T> {
    @Serializable
    data class Increase<out T>(
        val pct: Percentage,
        val value: T
    ) : ChangeRemark<T>()

    @Serializable
    data class Decrease<out T>(
        val pct: Percentage,
        val value: T
    ) : ChangeRemark<T>()

    @Serializable
    data class Fixed<out T>(
        val pct: Percentage = Percentage.ZERO,
        val at: T
    ) : ChangeRemark<T>()

    object Indeterminate : ChangeRemark<Nothing>()
}