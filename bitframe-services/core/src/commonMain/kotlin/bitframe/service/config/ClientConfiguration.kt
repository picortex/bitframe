package bitframe.service.config

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlin.jvm.JvmOverloads

open class ClientConfiguration @JvmOverloads constructor(
    override val appId: String,
    override val scope: CoroutineScope = CoroutineScope(SupervisorJob())
) : ServiceConfig(appId, scope)
