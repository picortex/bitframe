package bitframe

import kotlinx.serialization.StringFormat

interface ApiConfigRest<out E, out H> : ApiConfig {
    val endpoint: E
    val codec: StringFormat
    val http: H

    fun <E2> map(transform: (E) -> E2): ApiConfigRest<E2, H>
}