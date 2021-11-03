package bitframe.service.config

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlin.jvm.JvmOverloads
import kotlin.jvm.JvmStatic
import kotlin.jvm.JvmSynthetic

interface ServiceConfig {
    val scope: CoroutineScope

    companion object {
        @JvmStatic
        val DEFAULT_SCOPE
            get() = CoroutineScope(SupervisorJob())

        @JvmSynthetic
        operator fun invoke(
            scope: CoroutineScope = DEFAULT_SCOPE
        ): ServiceConfig = object : ServiceConfig {
            override val scope: CoroutineScope = scope
        }

        @JvmOverloads
        @JvmStatic
        fun create(scope: CoroutineScope = DEFAULT_SCOPE) = invoke(scope)
    }
}