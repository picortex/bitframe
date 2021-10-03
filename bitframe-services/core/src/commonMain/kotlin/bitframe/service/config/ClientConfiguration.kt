package bitframe.service.config

import kotlinx.coroutines.CoroutineScope

open class ClientConfiguration(
    open val appId: String,
    override val scope: CoroutineScope
) : ServiceConfig()
