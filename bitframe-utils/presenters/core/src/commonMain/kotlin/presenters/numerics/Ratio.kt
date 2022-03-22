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

    val asPercentageInt by lazy { value.roundToInt() * 100 }

    val asPercentageDouble by lazy { value * 100.0 }

    val toPercentage: Percentage by lazy { Percentage(value) }
}
