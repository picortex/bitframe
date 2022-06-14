package bitframe

import kotlinx.serialization.json.Json

interface ControllerConfig<out S> {
    val service: S
    val json: Json

    companion object {
        operator fun <S> invoke(service: S, json: Json): ControllerConfig<S> = ControllerConfigImpl(service, json)
    }
}