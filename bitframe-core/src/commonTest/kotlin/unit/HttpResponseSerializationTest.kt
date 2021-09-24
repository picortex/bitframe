package unit

import bitframe.http.HttpFailure
import bitframe.http.HttpSuccess
import bitframe.http.response.decodeResponseFromString
import bitframe.http.response.encodeResponseToString
import bitframe.http.response.responseOf
import expect.expect
import expect.toBe
import io.ktor.http.*
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlin.test.Test
import kotlin.test.fail

class HttpResponseSerializationTest {
    @Serializable
    data class Specie(val type: String)

    @Serializable
    data class Info(val gone: Boolean)

    @Test
    fun should_have_an_intuitive_syntax() {
        when (val resp = responseOf(Specie("Human"), Specie("Cat"))) {
            is HttpFailure -> fail()
            is HttpSuccess -> expect(resp.status.code).toBe(HttpStatusCode.OK.value)
        }
    }

    @Test
    fun should_serialize_a_response() {
        val resp = responseOf(Specie("Cat"), Info(false))
        println(resp)
        val json = Json.encodeResponseToString(Specie.serializer(), resp)
        println(json)
    }

    @Test
    fun should_serialize_a_response_with_info() {
        val resp = responseOf(Specie("Cat"), Info(false))
        println(resp)
        val json = Json.encodeResponseToString(Specie.serializer(), Info.serializer(), resp)
        println(json)
    }

    @Test
    fun should_deserialize_a_response_without_info() {
        val json = """{"status":{"code":200,"message":"OK"},"payload":{"data":{"type":"Cat"}}}"""
        val resp = Json.decodeResponseFromString(Specie.serializer(), json)
        expect(resp).toBe<HttpSuccess<*, *>>()
        when (resp) {
            is HttpFailure -> fail()
            is HttpSuccess -> {
                expect(resp.payload.info).toBe(null)
                expect(resp.payload.data.type).toBe("Cat")
            }
        }
    }

    @Test
    fun should_deserialize_a_response_with_info() {
        val json = """{"status":{"code":200,"message":"OK"},"payload":{"data":{"type":"Cat"},"info":{"gone":false}}}"""
        val resp = Json.decodeResponseFromString(Specie.serializer(), Info.serializer(), json)
        expect(resp).toBe<HttpSuccess<*, *>>()
        when (resp) {
            is HttpFailure -> fail()
            is HttpSuccess -> {
                expect(resp.payload.data.type).toBe("Cat")
                expect(resp.payload.info.gone).toBe(false)
            }
        }
    }

    @Test
    fun should_serialize_a_failure() {
        val resp = responseOf(Throwable("Fail for fun", Throwable("Test exception")))
        println(Json.encodeResponseToString(Specie.serializer(), resp))
    }

    @Test
    fun should_de_serialize_a_failure() {
        val json = """{"status":{"code":400,"message":"Bad Request"},"error":{"message":"Fail for fun","type":"kotlin.Throwable","cause":"Test exception"}}"""
        val resp = Json.decodeResponseFromString(Specie.serializer(), json)
        println(resp)
    }

    @Test
    fun should_return_a_failure_response_on_a_failed_deserialization() {
        val resp = Json.decodeResponseFromString(Specie.serializer(), "{}")
        println(resp)
    }
}