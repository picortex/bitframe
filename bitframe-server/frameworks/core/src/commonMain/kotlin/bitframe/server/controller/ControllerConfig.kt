package bitframe.server.controller

import kotlinx.serialization.KSerializer

interface ControllerConfig<D : Any> {
    val serializer: KSerializer<D>
}