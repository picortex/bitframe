package pimonitor.core.invites

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.Serializer
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@Serializer(forClass = InfoResults::class)
internal open class InfoResultsSerializer<T>(
    private val elementSerializer: KSerializer<T>
) : KSerializer<InfoResults<T>> {
    override val descriptor: SerialDescriptor = buildClassSerialDescriptor("InfoResults")

    override fun deserialize(decoder: Decoder) = try {
        InfoResults.Shared(decoder.decodeSerializableValue(elementSerializer))
    } catch (err: Throwable) {
        InfoResults.NotShared(decoder.decodeSerializableValue(String.serializer()))
    }

    override fun serialize(encoder: Encoder, value: InfoResults<T>) = when (value) {
        is InfoResults.NotShared -> encoder.encodeSerializableValue(String.serializer(), value.message)
        is InfoResults.Shared -> encoder.encodeSerializableValue(elementSerializer, value.data)
    }
}