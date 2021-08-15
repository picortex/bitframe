package bitframe.authentication

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlin.jvm.JvmOverloads

class TestClientConfiguration @JvmOverloads constructor(
    override val appId: String,
    val simulationTime: Int = 0,
    override val scope: CoroutineScope = CoroutineScope(SupervisorJob()),
) : ClientConfiguration(appId, scope)