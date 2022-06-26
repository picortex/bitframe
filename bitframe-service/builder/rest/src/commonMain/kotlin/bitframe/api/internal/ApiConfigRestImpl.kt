package bitframe.api.internal

import bitframe.ApiConfigRest
import kotlinx.serialization.StringFormat

class ApiConfigRestImpl<E, H>(
    override val endpoint: E,
    override val http: H,
    override val codec: StringFormat
) : ApiConfigRest<E, H>