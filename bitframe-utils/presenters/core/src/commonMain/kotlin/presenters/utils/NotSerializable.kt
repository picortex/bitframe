package presenters.utils

import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerializationException
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

object NotSerializable : KSerializer<Nothing> {
    override val descriptor: SerialDescriptor = buildClassSerialDescriptor("kotlin.Any")
    override fun deserialize(decoder: Decoder): Nothing = throw SerializationException("Not serializable")
    override fun serialize(encoder: Encoder, value: Nothing) = throw SerializationException("Not serializable")
}