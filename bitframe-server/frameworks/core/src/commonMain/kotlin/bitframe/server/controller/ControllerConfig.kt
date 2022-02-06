package bitframe.server.controller

import bitframe.server.service.GenericService
import kotlinx.serialization.KSerializer
import kotlinx.serialization.json.Json
import kotlin.jvm.JvmField

interface ControllerConfig<D : Any> {
    val serializer: KSerializer<D>
    val service: GenericService<D>
    val json: Json

    companion object {
        @JvmField
        val DEFAULT_JSON = Json { }

        operator fun <D : Any> invoke(
            serializer: KSerializer<D>,
            service: GenericService<D>,
            json: Json = DEFAULT_JSON
        ): ControllerConfig<D> = object : ControllerConfig<D> {
            override val serializer: KSerializer<D> = serializer
            override val service: GenericService<D> = service
            override val json: Json = json
        }
    }
}