package bitframe.server.modules

import bitframe.server.controller.Controller
import bitframe.server.controller.ControllerImpl
import kotlin.reflect.KClass

interface ModuleConfiguration<D : Any> {
    val clazz: KClass<D>
    val name: String
    val controller: Controller
    val basePath get() = "/api/${name.lowercase()}s"

    companion object {
        operator fun <D : Any> invoke(
            clazz: KClass<D>,
            name: String? = null,
            controller: Controller = ControllerImpl(),
        ) = object : ModuleConfiguration<D> {
            override val clazz = clazz
            override val name = name ?: clazz.simpleName ?: error("Provide a module name")
            override val controller = controller
        }

        inline operator fun <reified D : Any> invoke(
            name: String? = null,
            controller: Controller = ControllerImpl(),
        ) = invoke(D::class, name, controller)
    }
}