package unit

import bitframe.actors.users.Contacts
import expect.expect
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlin.test.Test

class ContactsSerializerTest {

    @Serializable
    data class Person(val contacts: Contacts)

    @Test
    fun should_minimally_serialize_a_contact() {
        val person = Person(Contacts("email@test.com"))
        val json = Json.encodeToString(Person.serializer(), person)
        expect(json).toBe("""{"contacts":["email@test.com"]}""")
    }

    @Test
    fun should_deserialize_a_contact() {
        val json = """{"contacts":["email@test.com"]}"""
        val obj = Json.decodeFromString(Person.serializer(), json)
        expect(obj).toBe(Person(Contacts("email@test.com")))
    }
}