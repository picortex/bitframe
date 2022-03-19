import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.Serializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@Serializer(forClass = Optional::class)
class OptionalSerializer<T>(val elementSerializer: KSerializer<T>) : KSerializer<Optional<T>> {

    override val descriptor: SerialDescriptor = buildClassSerialDescriptor("Optional")

    @Serializable
    enum class OptionalBag {
        Empty, Absent
    }

    override fun deserialize(decoder: Decoder): Optional<T> = try {
        decoder.decodeSerializableValue(Present.serializer(elementSerializer))
    } catch (err: Throwable) {
        null
    } ?: when (decoder.decodeSerializableValue(OptionalBag.serializer())) {
        OptionalBag.Absent -> Absent
        OptionalBag.Empty -> Empty()
    }

    override fun serialize(encoder: Encoder, value: Optional<T>) = when (value) {
        Absent -> encoder.encodeSerializableValue(OptionalBag.serializer(), OptionalBag.Absent)
        is Empty -> encoder.encodeSerializableValue(OptionalBag.serializer(), OptionalBag.Empty)
        is Present -> encoder.encodeSerializableValue(Present.serializer(elementSerializer), value)
    }
}