package bitframe.server.modules

import bitframe.server.actions.Action
import bitframe.server.http.HttpResponse
import bitframe.server.http.HttpRoute
import io.ktor.http.*
import kotlin.jvm.JvmOverloads
import kotlin.reflect.KClass

interface Module {
    val name: String
    val actions: List<Action>

    fun info() = mapOf(
        "name" to name,
        "actions" to actions.map { it.info() }
    )

    companion object {
        inline operator fun <reified D : Any> invoke(
            config: ModuleConfiguration<D> = ModuleConfiguration()
        ): Module = ModuleImpl(config)
    }
}

@JvmOverloads
fun <T : Any> Module(
    clazz: KClass<T>,
    name: String = clazz.simpleName ?: error("Provide a module name")
): Module = object : Module {
    override val name: String = name
    override val actions: List<Action> = listOf(
        Action("create", mapOf(), HttpRoute(HttpMethod.Post, "/") {
            HttpResponse(HttpStatusCode.OK)
        }),
        Action("single", mapOf(), HttpRoute(HttpMethod.Get, "/{uid}") {
            HttpResponse(HttpStatusCode.OK)
        }),
        Action("many", mapOf(), HttpRoute(HttpMethod.Get, "/all") {
            HttpResponse(HttpStatusCode.OK)
        }),
        Action("update", mapOf(), HttpRoute(HttpMethod.Put, "/") {
            HttpResponse(HttpStatusCode.OK)
        }),
        Action("delete", mapOf(), HttpRoute(HttpMethod.Delete, "/{uid}") {
            HttpResponse(HttpStatusCode.OK)
        })
    )
}