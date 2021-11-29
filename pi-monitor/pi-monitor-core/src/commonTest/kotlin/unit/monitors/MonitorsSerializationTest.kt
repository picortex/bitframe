package unit.monitors

import bitframe.authentication.users.Contacts
import bitframe.authentication.users.User
import identifier.Email
import kotlinx.serialization.json.Json
import bitframe.monitors.CooperateMonitor
import bitframe.monitors.CooperateMonitor.ContactPerson
import bitframe.monitors.IndividualMonitor
import kotlin.test.Test

class MonitorsSerializationTest {
    val userRef get() = User("", "", "", Contacts.None, null, spaces = listOf()).ref()

    @Test
    fun should_serialize_an_individual_monitor() {
        val monitor = IndividualMonitor(
            uid = "none",
            name = "John Doe",
            email = Email("john@doe.com"),
            userRef = userRef
        )
        val json = Json.encodeToString(IndividualMonitor.serializer(), monitor)
        println(json)
    }

    @Test
    fun should_de_serialize_a_cooperate_monitor() {
        val monitor = CooperateMonitor(
            uid = "none",
            name = "John Doe",
            email = Email("john@doe.com"),
            person = ContactPerson("", "Anderson", Email("anderson@test.com"), userRef)
        )
        val json = Json.encodeToString(CooperateMonitor.serializer(), monitor)
        println(json)
    }
}