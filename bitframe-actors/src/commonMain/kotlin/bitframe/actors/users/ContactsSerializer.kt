package bitframe.actors.users

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializer
import kotlinx.collections.interoperable.serializers.ListSerializer
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.descriptors.*
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@Serializer(forClass = Contacts::class)
object ContactsSerializer : KSerializer<Contacts> {
    @OptIn(ExperimentalSerializationApi::class, InternalSerializationApi::class)
    override val descriptor: SerialDescriptor = buildSerialDescriptor(
        serialName = "Contacts",
        kind = StructureKind.LIST
    )

    override fun deserialize(decoder: Decoder): Contacts {
        val list = decoder.decodeSerializableValue(ListSerializer(String.serializer()))
        return Contacts(*list.toTypedArray())
    }

    override fun serialize(encoder: Encoder, value: Contacts) {
        encoder.encodeSerializableValue(
            serializer = ListSerializer(String.serializer()),
            value = value.toListOfValues()
        )
    }
}