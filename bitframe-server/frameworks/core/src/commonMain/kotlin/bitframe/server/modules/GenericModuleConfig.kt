package bitframe.server.modules

import bitframe.core.Savable
import bitframe.server.controller.GenericController
import kotlin.reflect.KClass

interface GenericModuleConfig<D : Savable> {
    val clazz: KClass<D>
    val name: String
    val controller: GenericController<D>
    val basePath get() = "/api/$name"

    companion object {
        operator fun <D : Savable> invoke(
            clazz: KClass<D>,
            controller: GenericController<D>,
            name: String? = null,
            basePath: String? = null
        ) = object : GenericModuleConfig<D> {
            private val finalName = name ?: clazz.simpleName?.let { "${it.lowercase()}s" } ?: error("Provide a module name")
            override val clazz = clazz
            override val name = finalName
            override val controller = controller
            override val basePath: String = basePath ?: "/api/$finalName"
        }

        inline operator fun <reified D : Savable> invoke(
            controller: GenericController<D>,
            name: String? = null,
            basePath: String? = null
        ) = invoke(D::class, controller, name, basePath)
    }
}