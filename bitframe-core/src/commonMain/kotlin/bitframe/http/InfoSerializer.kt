package bitframe.http

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.mapper.Mapper
import kotlinx.serialization.mapper.WrappedMap

internal object InfoSerializer : KSerializer<WrappedMap> {

    @OptIn(InternalSerializationApi::class, ExperimentalSerializationApi::class)
    override val descriptor: SerialDescriptor = buildClassSerialDescriptor(
        "HttpPayload"
    )

    override fun serialize(encoder: Encoder, value: WrappedMap) {
        encoder.encodeString(Mapper.encodeToString(value))
    }

    override fun deserialize(decoder: Decoder): WrappedMap {
        return Mapper.decodeFromString(decoder.decodeString())
    }
}