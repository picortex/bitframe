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

    private val people = listOf("john", "juma", "anderson").mapIndexed { i, it ->
        Person(it, Contacts("email$i@test.com"))
    }

    @Test
    fun conditions_can_match_top_level_properties() {
        val condition = Condition("name", Condition.Operator.Equals, "anderson")
        val matches = people.matching(condition, Person.serializer())
        expect(matches).toBeOfSize(1)
    }

    @Test
    fun conditions_can_match_nested_level_properties() {
        val condition = Condition("contacts", Condition.Operator.Contains, "email1@test.com")
        val matches = people.matching(condition, Person.serializer())
        expect(matches).toBeOfSize(1)
    }

    @Test
    fun condition_can_match_multiple_levels() {
        val condition = Condition("contacts", Condition.Operator.Contains, "email")
        val matches = people.matching(condition, Person.serializer())
        expect(matches).toBeOfSize(3)
    }
}