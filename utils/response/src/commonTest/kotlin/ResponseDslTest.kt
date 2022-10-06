import response.response
import expect.expect
import io.ktor.http.*
import kotlinx.serialization.Serializable
import kotlin.test.Test

class ResponseDslTest {

    @Serializable
    data class Person(val name: String)

    @Serializable
    data class Info(val age: Int)

    @Test
    fun should_return_a_simple_response() {
        val resp = response {
            resolve(Person("John"))
        }
        expect(resp.status.code).toBe(HttpStatusCode.OK.value)
    }

    @Test
    fun should_have_a_fluid_build() {
        val int: Int? = 5
        val res = response {
            val i = int ?: badRequest("int must not be null")
            if (i < 4) {
                reject(HttpStatusCode.Unauthorized, "To small for this")
            }
            resolve(Person("John"))
        }
        println(res.status)
        expect(res.status.code).toBe(HttpStatusCode.OK.value)
    }
}