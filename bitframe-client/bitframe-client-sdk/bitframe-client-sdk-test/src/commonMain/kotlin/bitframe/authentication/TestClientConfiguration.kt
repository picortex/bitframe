package bitframe.authentication

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlin.jvm.JvmOverloads

class TestClientConfiguration @JvmOverloads constructor(
    override val appId: String,
    override val scope: CoroutineScope = CoroutineScope(SupervisorJob()),
    val simulationTime: Int = 0
) : ClientConfiguration(appId, scope)