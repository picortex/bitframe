package akkounts.regulation

import kotlin.jvm.JvmField

class Policy(
    val maxRequests: Int,
    val maxPeriod: Period
) {
    companion object {
        @JvmField
        val LOOSE = Policy(Int.MAX_VALUE, Period.INFINITE)
    }
}