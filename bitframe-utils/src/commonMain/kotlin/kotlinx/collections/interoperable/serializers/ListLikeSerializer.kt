package kotlinx.collections.interoperable.serializers

import kotlinx.collections.interoperable.ArrayList
import kotlinx.collections.interoperable.List
import kotlinx.collections.interoperable.MutableList
import kotlinx.collections.interoperable.listOf
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializer
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

internal abstract class ListLikeSerializer<E, L : List<E>>(val elementSerializer: KSerializer<E>) : KSerializer<L> {
    override val descriptor: SerialDescriptor = elementSerializer.descriptor
    override fun serialize(encoder: Encoder, value: L) {
        encoder.encodeSerializableValue(ListSerializer(elementSerializer), value)
    }

    override fun deserialize(decoder: Decoder): L = ArrayList(decoder.decodeSerializableValue(ListSerializer(elementSerializer))) as L
}

internal class ListSerializer<E>(elementSerializer: KSerializer<E>) : ListLikeSerializer<E, List<E>>(elementSerializer)

internal class MutableListSerializer<E>(elementSerializer: KSerializer<E>) : ListLikeSerializer<E, MutableList<E>>(elementSerializer)

internal class ArrayListSerializer<E>(elementSerializer: KSerializer<E>) : ListLikeSerializer<E, ArrayList<E>>(elementSerializer)