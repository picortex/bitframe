package bitframe.service.config

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlin.jvm.JvmField
import kotlin.jvm.JvmOverloads

open class ServiceConfig @JvmOverloads constructor(
    open val scope: CoroutineScope = CoroutineScope(SupervisorJob())
) {
    companion object {
        @JvmField
        val DEFAULT = ServiceConfig(
            scope = CoroutineScope(SupervisorJob())
        )
    }
}