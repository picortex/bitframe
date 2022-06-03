package pimonitor.core.invites

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerializationException
import kotlinx.serialization.Serializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.JsonDecoder
import kotlinx.serialization.json.JsonEncoder
import kotlinx.serialization.json.JsonObject
import pimonitor.core.businesses.MonitoredBusinessBasicInfo

//@Serializer(forClass = InfoResults::class)
//internal open class InfoResultsSerializer<T>(
//    private val elementSerializer: KSerializer<T>
//) : KSerializer<InfoResults<T>> {
//    override val descriptor: SerialDescriptor = buildClassSerialDescriptor("InfoResults")
//
//    @Serializable
//    private class NotSharedProxy(val business: MonitoredBusinessBasicInfo, val message: String)
//
//    override fun deserialize(decoder: Decoder) = try {
//        decoder.decodeSerializableValue(InfoResults.Shared.serializer(elementSerializer))
//    } catch (err: Throwable) {
//        println("here now")
//        println("err: ${err.message}")
//        try {
//            val ns = decoder.decodeSerializableValue(NotSharedProxy.serializer())
//            println("ns")
//            println(ns)
//            InfoResults.NotShared(ns.business, ns.message)
//        } catch (e: SerializationException) {
//            println(e.message)
//            throw e
//        }
//    }
//
//    override fun serialize(encoder: Encoder, value: InfoResults<T>) = when (value) {
//        is InfoResults.NotShared -> encoder.encodeSerializableValue(NotSharedProxy.serializer(), NotSharedProxy(value.business, value.message))
//        is InfoResults.Shared -> encoder.encodeSerializableValue(InfoResults.Shared.serializer(elementSerializer), value)
//    }
//}

@Serializer(forClass = InfoResults::class)
internal open class InfoResultsSerializer<T>(
    private val elementSerializer: KSerializer<T>
) : KSerializer<InfoResults<T>> {
    override val descriptor: SerialDescriptor = buildClassSerialDescriptor("InfoResults")

    @Serializable
    private class NotSharedProxy(val business: MonitoredBusinessBasicInfo, val message: String)

    private fun exception(): Nothing {
        throw SerializationException("InfoResults can only be encoded/decoded in Json format")
    }

    override fun deserialize(decoder: Decoder): InfoResults<T> {
        val input = decoder as? JsonDecoder ?: exception()
        val element = input.decodeJsonElement()
        val tree = element as? JsonObject ?: throw SerializationException("Expected JsonObject")
        return if (InfoResults.NotShared::message.name in tree) {
            val proxy = input.json.decodeFromJsonElement(NotSharedProxy.serializer(), tree)
            InfoResults.NotShared(proxy.business, proxy.message)
        } else {
            input.json.decodeFromJsonElement(InfoResults.Shared.serializer(elementSerializer), tree)
        }
    }

    override fun serialize(encoder: Encoder, value: InfoResults<T>) {
        val output = encoder as? JsonEncoder ?: exception()
        val tree = when (value) {
            is InfoResults.NotShared -> output.json.encodeToJsonElement(NotSharedProxy.serializer(), NotSharedProxy(value.business, value.message))
            is InfoResults.Shared -> output.json.encodeToJsonElement(InfoResults.Shared.serializer(elementSerializer), value)
        }
        output.encodeJsonElement(tree)
    }
}