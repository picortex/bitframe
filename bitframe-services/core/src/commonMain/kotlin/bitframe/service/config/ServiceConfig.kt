package bitframe.service.config

import bitframe.events.EventBus
import bitframe.events.InMemoryEventBus
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlin.jvm.JvmField
import kotlin.jvm.JvmOverloads
import kotlin.jvm.JvmStatic
import kotlin.jvm.JvmSynthetic

interface ServiceConfig {
    val bus: EventBus
    val scope: CoroutineScope

    companion object {

        @JvmField
        val DEFAULT_SCOPE = CoroutineScope(SupervisorJob())
        val DEFAULT_BUS = InMemoryEventBus()

        @JvmSynthetic
        operator fun invoke(
            bus: EventBus = DEFAULT_BUS,
            scope: CoroutineScope = DEFAULT_SCOPE
        ): ServiceConfig = object : ServiceConfig {
            override val bus = bus
            override val scope = scope
        }

        @JvmOverloads
        @JvmStatic
        fun create(
            bus: EventBus = DEFAULT_BUS,
            scope: CoroutineScope = DEFAULT_SCOPE
        ) = invoke(bus, scope)
    }
}