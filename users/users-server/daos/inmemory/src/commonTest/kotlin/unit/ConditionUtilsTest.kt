package unit

import bitframe.server.data.Condition
import expect.expect
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import users.server.matching
import users.user.Contacts
import kotlin.test.Test

class ConditionUtilsTest {

    @Serializable
    data class Person(
        val name: String,
        val contacts: Contacts
    )

    @Test
    fun conditions_can_match_easily() {
        val condition = Condition("name", Condition.Operator.Equals, "anderson")
        val people = listOf("john", "juma", "anderson").mapIndexed { i, it ->
            Person(it, Contacts("email$i@test.com")).also { p ->
                println(Json.encodeToString(Person.serializer(), p))
            }
        }
        expect(people.matching(condition, Person.serializer())).toBeOfSize(1)
    }
}