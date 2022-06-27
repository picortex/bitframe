package bitframe

import bitframe.internal.ControllerConfigImpl
import kotlinx.serialization.StringFormat
import kotlinx.serialization.json.Json

interface ControllerConfig<out S> {
    val service: S
    val codec: StringFormat

    companion object {
        operator fun <S> invoke(service: S, codec: StringFormat): ControllerConfig<S> = ControllerConfigImpl(service, codec)
    }
}