@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package presenters.containers

import presenters.numerics.Percentage
import kotlin.js.JsExport

sealed class ChangeRemark<out T> {

    data class Increase<out T>(
        val pct: Percentage,
        val value: T
    ) : ChangeRemark<T>()

    data class Decrease<out T>(
        val pct: Percentage,
        val value: T
    ) : ChangeRemark<T>()

    data class Fixed<out T>(
        val at: T
    ) : ChangeRemark<T>() {
        val pct: Percentage by lazy { Percentage.ZERO }
    }

    object Indeterminate : ChangeRemark<Nothing>()
}