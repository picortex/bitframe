package bitframe.daos.config

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.sync.Mutex
import kotlin.jvm.JvmField
import kotlin.jvm.JvmOverloads
import kotlin.jvm.JvmStatic
import kotlin.jvm.JvmSynthetic

interface MockDaoConfig : DaoConfig {
    val simulationTime: Long
    val lock: Mutex

    companion object {
        /**
         * The safe minimum simulation time for an InMemory Dao which is usually by a [List]
         */
        @JvmField
        val MIN_SIMULATION_TIME = 0L

        /**
         * The time (in milliseconds) for simulating data operations to get
         */
        @JvmField
        val DEFAULT_SIMULATION_TIME = MIN_SIMULATION_TIME

        @JvmField
        val DEFAULT_LOCK = Mutex()

        @JvmField
        val DEFAULT_SCOPE = DaoConfig.DEFAULT_SCOPE

        @JvmSynthetic
        operator fun invoke(
            simulationTime: Long = DEFAULT_SIMULATION_TIME,
            lock: Mutex = DEFAULT_LOCK,
            scope: CoroutineScope = DEFAULT_SCOPE
        ) = object : MockDaoConfig {
            override val simulationTime: Long = simulationTime
            override val lock: Mutex = lock
            override val scope: CoroutineScope = scope
        }

        @JvmOverloads
        @JvmStatic
        fun create(
            simulationTime: Long = DEFAULT_SIMULATION_TIME,
            lock: Mutex = DEFAULT_LOCK,
            scope: CoroutineScope = DEFAULT_SCOPE
        ) = invoke(simulationTime, lock, scope)
    }
}