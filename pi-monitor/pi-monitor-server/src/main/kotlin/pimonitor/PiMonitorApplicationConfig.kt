package pimonitor

import bitframe.ApplicationConfig
import bitframe.daos.DaoFactory
import bitframe.server.BitframeApplicationConfig.Companion.defaultModules
import bitframe.server.modules.Module
import bitframe.service.server.config.ServiceConfig
import kotlinx.collections.interoperable.toInteroperableMutableList
import pimonitor.server.PiMonitorService
import java.io.File

interface PiMonitorApplicationConfig : ApplicationConfig<PiMonitorService> {
    companion object {
        operator fun invoke(
            client: File,
            service: PiMonitorService,
            daoFactory: DaoFactory,
            modules: MutableList<Module> = mutableListOf(),
        ) = object : PiMonitorApplicationConfig {
            override val client: File = client
            override val service: PiMonitorService = service
            override val modules: MutableList<Module> = (modules + defaultModules(daoFactory)).toInteroperableMutableList()
        }

        operator fun invoke(
            client: File,
            config: ServiceConfig,
            daoFactory: DaoFactory,
            modules: MutableList<Module> = mutableListOf(),
        ): PiMonitorApplicationConfig {
            val service = PiMonitorService(config)
            return invoke(client, service, daoFactory, modules)
        }
    }
}