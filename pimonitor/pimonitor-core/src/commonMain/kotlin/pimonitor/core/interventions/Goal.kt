@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor.core.interventions

import akkounts.utils.unset
import bitframe.core.Savable
import kotlinx.serialization.Serializable
import presenters.numerics.Percentage
import kotlin.js.JsExport

@Serializable
data class Goal(
    override val uid: String = unset,
    val name: String,
    val initialValue: Double,
    val currentValue: Double,
    val targetValue: Double,
    override val deleted: Boolean = false
) : Savable {
    val progressPercentage by lazy {
        // Using linear interpolation
        // point u: (initialValue,0)
        // point i: (current,percentage)
        // point v: (targetValue,1.0)
        // slope (dy/dx) : (1.0 - 0.0) / (targetValue - initialValue)
        // percentage    : slope * (currentValue - initialValue)
        if (targetValue == initialValue) return@lazy Percentage.ONE_HUNDRED
        Percentage.fromRatio((currentValue - initialValue) / (targetValue - initialValue))
    }

    override fun copySavable(uid: String, deleted: Boolean): Savable = copy(uid = uid, deleted = deleted)
}