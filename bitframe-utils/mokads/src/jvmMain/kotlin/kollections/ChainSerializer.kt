package kollections

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializer
import kotlinx.serialization.builtins.ArraySerializer
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.descriptors.buildSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@Serializer(forClass = Chain::class)
actual class ChainSerializer<E> actual constructor(elementSerializer: KSerializer<E>) : KSerializer<Chain<E>> {

    private val serializer = ListSerializer(elementSerializer)

    actual override val descriptor: SerialDescriptor = buildClassSerialDescriptor("Chain")

    actual override fun deserialize(decoder: Decoder): Chain<E> = ArrayList(decoder.decodeSerializableValue(serializer))

    actual override fun serialize(encoder: Encoder, value: Chain<E>) {
        encoder.encodeSerializableValue(serializer, value.toList())
    }
}