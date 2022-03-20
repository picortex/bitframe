import response.payload.decodePayloadFromString
import expect.expect
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlin.test.Test

class PayloadDeSerializerTest {
    @Serializable
    data class Person(val name: String)

    @Test
    fun should_be_able_to_deserialize_a_payload() {
        val json = """{"data":{"name":"Juma"}}"""
        val payload = Json.decodePayloadFromString(Person.serializer(), json)
        expect(payload.data.name).toBe("Juma")
    }

    @Test
    fun should_deserialize_a_dta_with_info() {
        val json = """{"data":{"name":"Juma"},"info":{"name":"Peter"}}"""
        val payload = Json.decodePayloadFromString(
            dataSerializer = Person.serializer(),
            infoSerializer = Person.serializer(),
            json
        )
        expect(payload.data.name).toBe("Juma")
        expect(payload.info.name).toBe("Peter")
    }
}