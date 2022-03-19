package bitframe.core

import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerializationException
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

object NotSerializable : KSerializer<Any> {
    override val descriptor: SerialDescriptor = buildClassSerialDescriptor("kotlin.Any")
    override fun deserialize(decoder: Decoder): Any = throw SerializationException("Not serializable")
    override fun serialize(encoder: Encoder, value: Any) = throw SerializationException("Not serializable")
}