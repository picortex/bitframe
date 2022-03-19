import expect.expect
import expect.toBe
import kash.Money
import kash.USD
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlin.test.Test

class OptionalTest {
    @Test
    fun should_serialize_optional_primitives() {
        val os: Optional<String> = Present("Test")
        println(Json.encodeToString(os))
    }

    @Test
    fun should_serialize_optional_custom_types() {
        val oct: Optional<Money> = Present(100.USD)
        println(Json.encodeToString(oct))
    }


    @Test
    fun can_deserialize_optional_primitives() {
        val obj = Json.decodeFromString<Optional<String>>("""{"value":"Test"}""")
        expect(obj.get()).toBe("Test")
    }

    @Test
    fun can_deserialize_optional_custom_types() {
        val obj = Json.decodeFromString<Optional<Money>>("""{"value":{"amount":10000,"currency":"USD"}}""")
        expect(obj.get()).toBe(100.USD)
    }

    @Test
    fun can_serialize_empty_optionals() {
        val o: Optional<String> = Empty()
        println(Json.encodeToString(o))
    }

    @Test
    fun can_serialize_absent_optionals() {
        val o: Optional<String> = Absent
        println(Json.encodeToString(o))
    }

    @Test
    fun can_deserialize_empty_optionals() {
        val obj = Json.decodeFromString<Optional<Int>>(""""Empty"""")
        println(obj)
        expect(obj).toBe<Empty<Int>>()
    }

    @Test
    fun can_deserialize_absent_optionals() {
        val obj = Json.decodeFromString<Optional<Int>>(""""Absent"""")
        println(obj)
        expect(obj).toBe<Absent>()
    }

    @Serializable
    data class Person(
        val name: Optional<String>
    )

    @Test
    fun is_serializable_even_when_composed() {
        println(Json.encodeToString(Person(name = Absent)))
    }

    @Test
    fun is_deserializable_even_when_composed() {
        val obj = Json.decodeFromString<Person>("""{"name":"Absent"}""")
        println(obj)
        expect(obj.name).toBe(Absent)
    }
}