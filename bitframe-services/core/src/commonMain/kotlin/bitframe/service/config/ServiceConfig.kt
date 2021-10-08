package bitframe.service.config

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlin.jvm.JvmField
import kotlin.jvm.JvmOverloads

open class ServiceConfig @JvmOverloads constructor(
    open val appId: String,
    open val scope: CoroutineScope = CoroutineScope(SupervisorJob())
)