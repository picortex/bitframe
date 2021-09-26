package bitframe.authentication.configs

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlin.jvm.JvmField

class DaoConfig(
    val simulationTime: Int,
    val scope: CoroutineScope
) {
    companion object {
        @JvmField
        val DEFAULT = DaoConfig(
            simulationTime = 0,
            scope = CoroutineScope(SupervisorJob())
        )
    }
}