package bitframe.server.modules

import bitframe.core.Savable
import bitframe.server.controller.GenericController
import bitframe.server.controller.GenericControllerConfig
import bitframe.service.server.GenericService
import kotlinx.serialization.serializer

inline fun <reified T : Savable> GenericModule(service: GenericService<T>, at: String? = null): GenericModule<T> {
    val controllerConfig = GenericControllerConfig(
        service = service,
        serializer = serializer()
    )
    val config = GenericModuleConfig(
        controller = GenericController(controllerConfig),
        name = at
    )
    return GenericModule(config)
}