package bitframe.core

import bitframe.daos.config.DatabaseConfigurationMock
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.sync.Mutex
import kotlin.jvm.JvmField
import kotlin.jvm.JvmOverloads
import kotlin.jvm.JvmStatic
import kotlin.reflect.KClass

interface MockDaoFactoryConfig : DatabaseConfigurationMock{
    override val simulationTime: Long
    val lock: Mutex
    val scope: CoroutineScope

    companion object {
        operator fun invoke(
            simulationTime: Long = MockDaoConfig.DEFAULT_SIMULATION_TIME,
            lock: Mutex = MockDaoConfig.DEFAULT_LOCK,
            scope: CoroutineScope = MockDaoConfig.DEFAULT_SCOPE
        ): MockDaoFactoryConfig = object : MockDaoFactoryConfig {
            override val simulationTime = simulationTime
            override val lock = lock
            override val scope = scope
        }

        @JvmStatic
        @JvmOverloads
        fun create(
            simulationTime: Long = MockDaoConfig.DEFAULT_SIMULATION_TIME,
            lock: Mutex = MockDaoConfig.DEFAULT_LOCK,
            scope: CoroutineScope = MockDaoConfig.DEFAULT_SCOPE
        ) = invoke(simulationTime, lock, scope)
    }
}