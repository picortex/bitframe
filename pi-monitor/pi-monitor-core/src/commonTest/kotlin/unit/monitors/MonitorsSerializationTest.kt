package unit.monitors

import bitframe.core.User
import identifier.Email
import kotlinx.collections.interoperable.listOf
import kotlinx.serialization.json.Json
import pimonitor.monitors.CooperateMonitor.ContactPerson
import kotlin.test.Test

class MonitorsSerializationTest {
    val userRef get() = User("", "", listOf(), listOf(), null).ref()

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