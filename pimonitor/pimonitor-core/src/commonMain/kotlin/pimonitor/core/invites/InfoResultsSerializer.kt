package pimonitor.core.invites

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.Serializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlin.js.JsName

@Serializer(forClass = InfoResults.Shared::class)
internal open class InfoResultsSerializer<T>(
    private val elementSerializer: KSerializer<T>
) : KSerializer<InfoResults<T>> {
    override val descriptor: SerialDescriptor = buildClassSerialDescriptor("InfoResults")

    override fun deserialize(decoder: Decoder) = try {
        InfoResults.NotShared(decoder.decodeString())
    } catch (err: Throwable) {
        InfoResults.Shared(decoder.decodeSerializableValue(elementSerializer))
    }

    override fun serialize(encoder: Encoder, value: InfoResults<T>) = when (value) {
        is InfoResults.NotShared -> encoder.encodeString(value.message)
        is InfoResults.Shared -> encoder.encodeSerializableValue(elementSerializer, value.data)
    }
}