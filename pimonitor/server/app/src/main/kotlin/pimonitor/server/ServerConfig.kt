package pimonitor.server

import akkounts.sage.SageOneZAService
import bitframe.core.DaoFactory
import bitframe.server.ServiceConfig
import events.EventBus
import kotlinx.coroutines.CoroutineScope
import kotlinx.serialization.json.Json
import logging.Logger
import mailer.Mailer
import pimonitor.core.picortex.PiCortexService

data class ServerConfig(
    val environment: String,
    val database: DaoFactory,
    override val sage: SageOneZAService,
    override val picortex: PiCortexService,
    override val mailer: Mailer,
    override val bus: EventBus = ServiceConfig.DEFAULT_BUS,
    override val logger: Logger = ServiceConfig.DEFAULT_LOGGER,
    override val json: Json = ServiceConfig.DEFAULT_JSON,
    override val scope: CoroutineScope = ServiceConfig.DEFAULT_SCOPE,
) : MonitorServiceConfig, ServiceConfig {
    override val daoFactory by lazy { database }
    override fun toString(): String = buildString {
        appendLine("ServerConfig(")
        appendLine("\tenvironment=$environment")
        appendLine("\tmailer=$mailer")
        appendLine("\tdatabase=$database")
        appendLine("\tsage=$sage")
        appendLine("\tpicortex=$picortex")
        appendLine(")")
    }
}