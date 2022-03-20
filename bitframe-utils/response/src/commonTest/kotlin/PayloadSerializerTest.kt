import response.payload.encodePayloadToString
import response.payload.payloadOf
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlin.test.Test

class PayloadSerializerTest {
    @Serializable
    data class Person(val name: String)

    @Test
    fun should_be_able_to_instantiate_payload_with_one_generic_parameter() {
        val pl = payloadOf(Person(name = "Juma"))
        println(pl)
    }

    @Test
    fun should_be_abel_to_serialize_payload_with_custom_info() {
        val pl = payloadOf(Person(name = "Juma"), Person(name = "Peter"))
        println(Json.encodePayloadToString(Person.serializer(), pl))
        println(Json.encodePayloadToString(Person.serializer(), Person.serializer(), pl))
    }
}