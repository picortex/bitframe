package bitframe.daos.config

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlin.jvm.JvmField
import kotlin.jvm.JvmOverloads
import kotlin.jvm.JvmStatic
import kotlin.jvm.JvmSynthetic

interface InMemoryDaoConfig : DaoConfig {
    val simulationTime: Long

    companion object {
        val DEFAULT_SIMULTATION_TIME = 0L
        val DEFAULT_SCOPE = DaoConfig.DEFAULT_SCOPE

        @JvmSynthetic
        operator fun invoke(
            simulationTime: Long = DEFAULT_SIMULTATION_TIME,
            scope: CoroutineScope = DEFAULT_SCOPE
        ) = object : InMemoryDaoConfig {
            override val simulationTime: Long = simulationTime
            override val scope: CoroutineScope = scope
        }

        @JvmOverloads
        @JvmStatic
        fun create(
            simulationTime: Long = DEFAULT_SIMULTATION_TIME,
            scope: CoroutineScope = DEFAULT_SCOPE
        ) = invoke(simulationTime, scope)
    }
}