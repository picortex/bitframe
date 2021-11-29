package bitframe.daos.config

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlin.jvm.JvmField
import kotlin.jvm.JvmOverloads
import kotlin.jvm.JvmStatic
import kotlin.jvm.JvmSynthetic

interface DaoConfig {
    val scope: CoroutineScope

    companion object {
        @JvmField
        val DEFAULT_SCOPE = CoroutineScope(SupervisorJob())

        @JvmSynthetic
        operator fun invoke(scope: CoroutineScope = DEFAULT_SCOPE) = object : DaoConfig {
            override val scope: CoroutineScope = scope
        }

        @JvmOverloads
        @JvmStatic
        fun create(scope: CoroutineScope = DEFAULT_SCOPE) = invoke(scope)
    }
}