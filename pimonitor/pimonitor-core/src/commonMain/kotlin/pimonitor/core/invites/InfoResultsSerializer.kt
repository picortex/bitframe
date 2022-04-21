package pimonitor.core.invites

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.Serializer
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import pimonitor.core.businesses.MonitoredBusinessBasicInfo

@Serializer(forClass = InfoResults::class)
internal open class InfoResultsSerializer<T>(
    private val elementSerializer: KSerializer<T>
) : KSerializer<InfoResults<T>> {
    override val descriptor: SerialDescriptor = buildClassSerialDescriptor("InfoResults")

    @Serializable
    private class NotShared(val business: MonitoredBusinessBasicInfo, val message: String)

    override fun deserialize(decoder: Decoder) = try {
        decoder.decodeSerializableValue(InfoResults.Shared.serializer(elementSerializer))
    } catch (err: Throwable) {
        val ns = decoder.decodeSerializableValue(NotShared.serializer())
        InfoResults.NotShared(ns.business, ns.message)
    }

    override fun serialize(encoder: Encoder, value: InfoResults<T>) = when (value) {
        is InfoResults.NotShared -> encoder.encodeSerializableValue(NotShared.serializer(), NotShared(value.business, value.message))
        is InfoResults.Shared -> encoder.encodeSerializableValue(InfoResults.Shared.serializer(elementSerializer), value)
    }
}