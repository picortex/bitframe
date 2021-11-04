package bitframe

import bitframe.events.EventBus
import bitframe.server.BitframeApplicationConfig
import bitframe.server.data.DAOProvider
import bitframe.server.modules.Module
import cache.Cache
import cache.MockCache
import kotlinx.coroutines.CoroutineScope
import java.io.File

interface ApplicationConfig<out P : DAOProvider> : BitframeApplicationConfig<P> {
    val client: File

    companion object {
        operator fun <P : DAOProvider> invoke(
            daoProvider: P,
            client: File,
            cache: Cache = MockCache(),
            bus: EventBus = BitframeApplicationConfig.DEFAULT_EVENT_BUS,
            module: MutableList<Module> = mutableListOf(),
            scope: CoroutineScope = BitframeApplicationConfig.DEFAULT_SCOPE,
        ) = object : ApplicationConfig<P> {
            override val client: File = client
            override val daoProvider: P = daoProvider
            override val cache: Cache = cache
            override val bus: EventBus = bus
            override val modules: MutableList<Module> = module
            override val scope: CoroutineScope = scope
        }
    }
}