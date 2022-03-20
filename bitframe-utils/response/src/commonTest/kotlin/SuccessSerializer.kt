import response.Success
import response.success.decodeSuccessFromString
import response.success.encodeSuccessToString
import expect.expect
import kotlinx.collections.interoperable.listOf
import kotlinx.collections.interoperable.serializers.ListSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlin.test.Test

class SuccessSerializer {
    @Serializable
    data class Person(val name: String = "John Doe", val age: Int = 23)

    @Serializable
    data class TestInfo(val registeredOn: Long)

    private val json = Json {
        encodeDefaults = true
    }

    @Test
    fun should_serialize_a_basic_success() {
        val success = Success.of(Person())
        println(success)
        println(json.encodeSuccessToString(Person.serializer(), success))
    }

    @Test
    fun should_serialize_a_list_success() {
        val success = Success.of(listOf(Person()))
        println(success)
        println(json.encodeSuccessToString(ListSerializer(Person.serializer()), success))
    }

    @Test
    fun should_serialize_a_success_with_an_informed_payload() {
        val success = Success.of(Person(), TestInfo(registeredOn = 1234))
        println(success)
        println(json.encodeSuccessToString(Person.serializer(), TestInfo.serializer(), success))
    }

    @Test
    fun should_deserialize_a_payload_with_info() {
        val input = """{"status":{"code":200,"message":"OK"},"payload":{"data":{"name":"John Doe","age":23},"info":{"registeredOn":1234}}}"""
        val success = json.decodeSuccessFromString(Person.serializer(), input)
        expect(success.payload.data.name).toBe("John Doe")
    }

    @Test
    fun should_deserialize_a_payload_without_info() {
        val input = """{"status":{"code":200,"message":"OK"},"payload":{"data":{"name":"John Doe","age":23},"info":{"registeredOn":1234}}}"""
        val success = json.decodeSuccessFromString(Person.serializer(), TestInfo.serializer(), input)
        val payload = success.payload
        expect(payload.data.name).toBe("John Doe")
        expect(payload.info.registeredOn).toBe(1234)
    }
}