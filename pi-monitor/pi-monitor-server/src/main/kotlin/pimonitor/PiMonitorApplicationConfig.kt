package pimonitor

import bitframe.ApplicationConfig
import bitframe.server.modules.Module
import bitframe.service.server.config.ServiceConfig
import pimonitor.server.PiMonitorService
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
            config: ServiceConfig,
            modules: MutableList<Module> = mutableListOf(),
        ): PiMonitorApplicationConfig {
            val service = PiMonitorService(config)
            return invoke(client, service, modules)
        }
    }
}