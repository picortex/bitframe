package bitframe.internal

import bitframe.ControllerConfig
import kotlinx.serialization.json.Json

internal class ControllerConfigImpl<out S>(
    override val service: S,
    override val json: Json
) : ControllerConfig<S>