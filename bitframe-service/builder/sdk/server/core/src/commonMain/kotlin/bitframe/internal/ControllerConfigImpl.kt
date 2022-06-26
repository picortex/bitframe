package bitframe.internal

import bitframe.ControllerConfig
import kotlinx.serialization.StringFormat

internal class ControllerConfigImpl<out S>(
    override val service: S,
    override val codec: StringFormat
) : ControllerConfig<S>