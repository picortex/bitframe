package unit

import bitframe.http.HttpSuccess
import bitframe.http.success.decodeSuccessFromString
import bitframe.http.success.encodeSuccessToString
import expect.expect
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlin.test.Test

class HttpSuccessSerializer {
    @Serializable
    data class Person(val name: String = "John Doe", val age: Int = 23)

    @Serializable
    data class TestInfo(val registeredOn: Long)

    private val json = Json {
        encodeDefaults = true
    }

    @Test
    fun should_serialize_a_basic_success() {
        val success = HttpSuccess.of(Person())
        println(success)
        println(json.encodeSuccessToString(Person.serializer(), success))
    }

    @Test
    fun should_serialize_a_success_with_an_informed_payload() {
        val success = HttpSuccess.of(Person(), TestInfo(registeredOn = 1234))
        println(success)
        println(json.encodeSuccessToString(Person.serializer(), TestInfo.serializer(), success))
    }

    @Test
    fun should_deserialize_a_payload_with_info() {
        val input = """{"status":{"code":200,"message":"OK"},"payload":{"data":{"name":"John Doe","age":23},"info":{"registeredOn":1234}}}"""
        val success = json.decodeSuccessFromString(Person.serializer(), input)
        expect(success.payLoad.data.name).toBe("John Doe")
    }

    @Test
    fun should_deserialize_a_payload_without_info() {
        val input = """{"status":{"code":200,"message":"OK"},"payload":{"data":{"name":"John Doe","age":23},"info":{"registeredOn":1234}}}"""
        val success = json.decodeSuccessFromString(Person.serializer(), TestInfo.serializer(), input)
        val payload = success.payLoad
        expect(payload.data.name).toBe("John Doe")
        expect(payload.meta?.registeredOn).toBe(1234)
    }
}