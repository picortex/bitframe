@file:UseSerializers(ChainSerializer::class)
@file:Suppress("NON_EXPORTABLE_TYPE")

package kollections

import expect.expect
import expect.toBe
import kollections.*
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import kotlinx.serialization.json.Json
import kotlin.js.JsExport
import kotlin.test.Ignore
import kotlin.test.Test

@Serializable
@JsExport
class Person(
    val name: String = "John Doe",
    val friends: Chain<Person> = chainOf()
)

@JsExport
data class Employee(
    val name: String,
    val tasks: Chain<String> = chainOf()
)

class ChainTest {
    @Test
    fun a_chain_can_be_easily_created() {
        val chain = chainOf(1, 2, 3)
        expect(chain[0]).toBe(1)
        expect(chain.size).toBe(3)
    }

    @Test
    fun can_be_used_inside_a_normal_class() {
        val p = Person("Jane")
        expect(p.name).toBe("Jane")
    }

    @Test
    @Ignore
    fun can_be_used_inside_a_data_class() {
        expect(Employee("John")).toBe(Employee("John"))
    }

    @Test
    fun can_be_serializable() {
        val json = Json.encodeToString(Person.serializer(), Person("Andy"))
        val person = Json.decodeFromString(Person.serializer(), json)
        expect(person.name).toBe("Andy")
    }

    @Test
    @Ignore
    fun should_be_mappable() {
        val chain = chainOf(1, 2, 3).map { it * 2 }
        expect<Chain<Int>>(chain).toBe(chainOf(2, 4, 6))
    }
}