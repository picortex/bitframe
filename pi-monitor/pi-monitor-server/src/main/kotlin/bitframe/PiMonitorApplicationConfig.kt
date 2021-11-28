package bitframe

import bitframe.events.EventBus
import bitframe.server.modules.Module
import kotlinx.coroutines.CoroutineScope
import bitframe.server.PiMonitorDaoProvider
import bitframe.server.PiMonitorService
import bitframe.server.PiMonitorServiceConfig
import java.io.File

interface PiMonitorApplicationConfig : ApplicationConfig<PiMonitorService> {
    companion object {
        operator fun invoke(
            client: File,
            service: PiMonitorService,
            modules: MutableList<Module> = mutableListOf(),
        ) = object : PiMonitorApplicationConfig {
            override val client: File = client
            override val service: PiMonitorService = service
            override val modules: MutableList<Module> = modules
        }

        operator fun invoke(
            client: File,
            bus: EventBus = PiMonitorServiceConfig.DEFAULT_BUS,
            provider: PiMonitorDaoProvider,
            modules: MutableList<Module> = mutableListOf(),
            scope: CoroutineScope = PiMonitorServiceConfig.DEFAULT_SCOPE
        ): PiMonitorApplicationConfig {
            val config = PiMonitorServiceConfig(bus, provider, scope)
            val service = PiMonitorService(config)
            return invoke(client, service, modules)
        }
    }
}