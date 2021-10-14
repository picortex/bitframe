package unit.monitors

import bitframe.authentication.users.Contacts
import bitframe.authentication.users.User
import contacts.Email
import kotlinx.serialization.json.Json
import pimonitor.monitors.CooperateMonitor
import pimonitor.monitors.IndividualMonitor
import pimonitor.monitors.Monitor
import kotlin.test.Test

class MonitorsSerializationTest {
    val userRef get() = User("", "", "", Contacts.None, null, spaces = listOf()).ref()

    @Test
    fun should_serialize_an_individual_monitor() {
        val monitor: Monitor = IndividualMonitor(
            uid = "none",
            name = "John Doe",
            email = Email("john@doe.com"),
            userRef = userRef
        )
        val json = Json.encodeToString(Monitor.serializer(), monitor)
        println(json)
    }

    @Test
    fun should_de_serialize_a_cooperate_monitor() {
        val monitor: Monitor = CooperateMonitor(
            uid = "none",
            name = "John Doe",
            email = Email("john@doe.com"),
            person = CooperateMonitor.ContactPerson("", "Anderson", Email("anderson@test.com"), userRef)
        )
        val json = Json.encodeToString(Monitor.serializer(), monitor)
        println(json)
    }
}