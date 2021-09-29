package bitframe.authentication.config

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class ServiceConfig(
    val scope: CoroutineScope = CoroutineScope(SupervisorJob())
)