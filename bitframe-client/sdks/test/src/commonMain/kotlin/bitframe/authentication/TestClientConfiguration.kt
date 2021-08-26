package bitframe.authentication

import bitframe.coroutines.Dispatchers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlin.jvm.JvmOverloads
import kotlin.jvm.JvmStatic
import kotlin.jvm.JvmSynthetic

class TestClientConfiguration @JvmOverloads private constructor(
    override val appId: String,
    /** The time (in milliseconds) a client would take to simulate fake activity */
    val simulationTime: Int = 0,
    override val scope: CoroutineScope,
) : ClientConfiguration(appId, scope) {
    companion object {
        private val cachedConfigs = mutableMapOf<String, TestClientConfiguration>()

        @JvmSynthetic
        operator fun invoke(
            appId: String,
            simulationTime: Int = 0,
            scope: CoroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Universal)
        ): TestClientConfiguration = cachedConfigs.getOrPut(appId) {
            TestClientConfiguration(appId, simulationTime, scope)
        }

        @JvmStatic
        @JvmOverloads
        fun of(
            appId: String,
            simulationTime: Int = 0,
            scope: CoroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Universal)
        ) = invoke(appId, simulationTime, scope)
    }
}