package bitframe.server

import bitframe.core.Savable
import kotlinx.serialization.KSerializer
import kotlinx.serialization.json.Json
import kotlin.jvm.JvmField

interface GenericControllerConfig<D : Savable> {
    val serializer: KSerializer<D>
    val service: GenericService<D>
    val json: Json

    companion object {
        @JvmField
        val DEFAULT_JSON = Json { }

        operator fun <D : Savable> invoke(
            serializer: KSerializer<D>,
            service: GenericService<D>,
            json: Json = DEFAULT_JSON
        ): GenericControllerConfig<D> = object : GenericControllerConfig<D> {
            override val serializer: KSerializer<D> = serializer
            override val service: GenericService<D> = service
            override val json: Json = json
        }
    }
}