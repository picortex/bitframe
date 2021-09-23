@file:UseSerializers(InfoSerializer::class)

package bitframe.http

import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import kotlinx.serialization.mapper.WrappedMap

@Serializable
data class HttpPayload<out D>(
    val data: D,
    @Serializable(with = InfoSerializer::class)
    val info: WrappedMap? = null,
) {
    constructor(data: D, info: Any?) : this(data)
}