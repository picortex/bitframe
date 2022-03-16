package kollections

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.Serializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.descriptors.buildSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@Serializer(forClass = Chain::class)
expect class ChainSerializer<E>(elementSerializer: KSerializer<E>) : KSerializer<Chain<E>> {

    override val descriptor: SerialDescriptor

    override fun deserialize(decoder: Decoder): Chain<E>

    override fun serialize(encoder: Encoder, value: Chain<E>)
}