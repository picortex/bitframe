package bitframe.server.modules

import bitframe.daos.DaoFactory
import bitframe.modal.HasId
import bitframe.server.controller.Controller
import bitframe.server.controller.ControllerConfig
import bitframe.server.service.GenericService
import bitframe.server.service.GenericServiceConfig
import kotlinx.serialization.KSerializer
import kotlinx.serialization.serializer
import kotlin.reflect.KClass

interface ModuleConfiguration<D : Any> {
    val clazz: KClass<D>
    val name: String
    val controller: Controller
    val basePath get() = "/api/$name"

    companion object {
        operator fun <D : HasId> invoke(
            clazz: KClass<D>,
            daoFactory: DaoFactory,
            serializer: KSerializer<D>,
            name: String? = null,
            controller: Controller? = null,
        ) = object : ModuleConfiguration<D> {
            override val clazz = clazz
            override val name = name ?: clazz.simpleName?.let { "${it.lowercase()}s" } ?: error("Provide a module name")
            override val controller = controller ?: run {
                val serviceConfig = GenericServiceConfig(daoFactory, clazz)
                val service = GenericService(serviceConfig)
                val controllerConfig = ControllerConfig(
                    serializer = serializer,
                    service = service
                )
                Controller(controllerConfig)
            }
        }

        inline operator fun <reified D : HasId> invoke(
            daoFactory: DaoFactory,
            serializr: KSerializer<D>? = null,
            name: String? = null,
            controller: Controller? = null,
        ) = invoke(D::class, daoFactory, serializr ?: serializer(), name, controller)
    }
}