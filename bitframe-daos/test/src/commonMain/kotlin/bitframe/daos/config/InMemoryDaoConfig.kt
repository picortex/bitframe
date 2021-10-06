package bitframe.daos.config

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlin.jvm.JvmField

open class InMemoryDaoConfig(
    val simulationTime: Int,
    override val scope: CoroutineScope = CoroutineScope(SupervisorJob())
) : DaoConfig {
    companion object {
        @JvmField
        val DEFAULT = InMemoryDaoConfig(
            simulationTime = 0,
            scope = CoroutineScope(SupervisorJob())
        )
    }
}