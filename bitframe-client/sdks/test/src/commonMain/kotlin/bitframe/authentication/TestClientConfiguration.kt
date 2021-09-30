package bitframe.authentication

import bitframe.authentication.config.ServiceConfig
import bitframe.daos.config.TestDaoConfig
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlin.jvm.JvmOverloads
import kotlin.jvm.JvmStatic
import kotlin.jvm.JvmSynthetic

class TestClientConfiguration @JvmOverloads private constructor(
    override val appId: String,
    /** The time (in milliseconds) a client would take to simulate fake activity */
    val simulationTime: Int = DEFAULT_SIMULATION_TIME,
    override val scope: CoroutineScope,
) : ClientConfiguration(appId, scope) {
    companion object {
        const val DEFAULT_SIMULATION_TIME = 0
        private val cachedConfigs = mutableMapOf<String, TestClientConfiguration>()

        @JvmSynthetic
        operator fun invoke(
            appId: String,
            simulationTime: Int = DEFAULT_SIMULATION_TIME,
            scope: CoroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Default)
        ): TestClientConfiguration = cachedConfigs.getOrPut(appId) {
            TestClientConfiguration(appId, simulationTime, scope)
        }

        @JvmStatic
        @JvmOverloads
        fun of(
            appId: String,
            simulationTime: Int = DEFAULT_SIMULATION_TIME,
            scope: CoroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Default)
        ) = invoke(appId, simulationTime, scope)
    }

    fun toDaoConfig() = TestDaoConfig(simulationTime, scope)

    fun toServiceConfig() = ServiceConfig(scope)
}