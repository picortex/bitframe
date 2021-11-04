package bitframe.server

import bitframe.events.EventBus
import bitframe.events.InMemoryEventBus
import bitframe.server.data.DAOProvider
import bitframe.server.modules.Module
import cache.Cache
import cache.MockCache
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

interface BitframeApplicationConfig<out P : DAOProvider> {
    val cache: Cache
    val bus: EventBus
    val daoProvider: P
    val modules: MutableList<Module>
    val scope: CoroutineScope

    fun with(vararg mods: Module): BitframeApplicationConfig<P> {
        modules.addAll(mods)
        return this
    }

    companion object {
        val DEFAULT_EVENT_BUS = InMemoryEventBus()
        val DEFAULT_SCOPE = CoroutineScope(SupervisorJob())
        operator fun <P : DAOProvider> invoke(
            daoProvider: P,
            cache: Cache = MockCache(),
            bus: EventBus = DEFAULT_EVENT_BUS,
            module: MutableList<Module> = mutableListOf(),
            scope: CoroutineScope = DEFAULT_SCOPE
        ) = object : BitframeApplicationConfig<P> {
            override val cache: Cache = cache
            override val bus: EventBus = bus
            override val daoProvider: P = daoProvider
            override val modules: MutableList<Module> = module
            override val scope: CoroutineScope = scope
        }
    }
}