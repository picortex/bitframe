package unit

import bitframe.http.HttpPayload
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlin.test.Test

class HttpPayloadSerializerTest {
    @Serializable
    data class Person(val name: String)

    @Test
    fun should_serialize_a_payload() {
        val pl = HttpPayload(Person("Anderson"))
        println(pl)
        println(Json.encodeToString(HttpPayload.serializer(Person.serializer()), pl))
        TODO()
    }
}