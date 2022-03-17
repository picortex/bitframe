@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package presenters.numerics

import kotlinx.serialization.Serializable
import kotlin.js.JsExport
import kotlin.math.roundToInt

@Serializable
data class Ratio(
    val value: Double
) {
    companion object {
        fun from(value: Number) = Ratio(value.toDouble())
    }

    val asPercentageInt: Int get() = value.roundToInt() * 100

    val asPercentageDouble: Double get() = value * 100.0

    val toPercentage: Percentage get() = Percentage(value)
}
