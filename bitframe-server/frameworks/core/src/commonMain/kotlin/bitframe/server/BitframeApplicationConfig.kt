package bitframe.server

import bitframe.events.InMemoryEventBus
import bitframe.server.modules.Module
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

interface BitframeApplicationConfig<out S : BitframeService> {
    val service: S
    val modules: MutableList<Module>

    companion object {
        val DEFAULT_EVENT_BUS = InMemoryEventBus()
        val DEFAULT_SCOPE = CoroutineScope(SupervisorJob())
        operator fun <S : BitframeService> invoke(
            service: S,
            module: MutableList<Module> = mutableListOf(),
        ) = object : BitframeApplicationConfig<S> {
            override val service = service
            override val modules: MutableList<Module> = module
        }
    }
}