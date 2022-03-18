@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package presenters.numerics

import kotlinx.serialization.Serializable
import kotlin.js.JsExport
import kotlin.jvm.JvmField
import kotlin.jvm.JvmStatic
import kotlin.math.roundToInt

@Serializable
data class Percentage(
    val asDouble: Double
) {
    companion object {
        @JvmField
        val ZERO = Percentage(0.0)

        @JvmField
        val ONE_HUNDRED = Percentage(100.0)

        @JvmStatic
        fun fromRatio(ratio: Number) = Percentage(ratio.toDouble() * 100)
    }

    val asInt get() = asDouble.roundToInt()

    val asRatio get() = asDouble / 100.0

    val toRatio get() = Ratio(asRatio)
}