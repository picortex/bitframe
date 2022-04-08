package datetime.serializers

import datetime.Date
import datetime.DateFormatter
import datetime.DateImpl
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@Serializer(forClass = Date::class)
object DateSerializer : KSerializer<Date> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("datetime.Date", PrimitiveKind.STRING)

    override fun deserialize(decoder: Decoder): Date = DateImpl.parse(decoder.decodeString())

    override fun serialize(encoder: Encoder, value: Date) = encoder.encodeString(DateFormatter("{YYYY}-{MM}-{DD}").format(value))
}