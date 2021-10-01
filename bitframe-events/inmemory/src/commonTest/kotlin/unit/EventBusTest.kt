package unit

import bitframe.events.Event
import bitframe.events.EventBus
import bitframe.events.InMemoryEventBus
import expect.expect
import kotlinx.coroutines.runTest
import kotlin.test.Test

class EventBusTest {
    val bus: EventBus = InMemoryEventBus()

    @Test
    fun should_be_able_to_dispatch_a_event() {
        val event = Event("test_event", 23)
        bus.dispatch(event)
    }

    @Test
    fun should_be_able_to_subscribe_to_events() {
        val event = Event("test-event", 7)
        var receivedData: Int? = null
        bus.subscribe("test-event") { data: Int ->
            receivedData = data
        }
        bus.dispatch(event)
        expect(receivedData).toBe(7)
    }

    @Test
    fun subscribers_should_listen_only_to_the_event_id_the_specify() {
        val event1 = Event("test-event-1", 1)
        val event2 = Event("test-event-2", 2)
        var receivedData: Int? = null
        bus.subscribe("test-event-1") { data: Int ->
            receivedData = data
        }
        bus.dispatch(event1)
        bus.dispatch(event2)
        expect(receivedData).toBe(1)
    }

    @Test
    fun should_be_able_to_post_custom_data() {
        data class Person(val name: String)

        val person = Person("John")
        val event = Event("created_person", person)
        var called: Person? = null
        bus.subscribe("created_person") { data: Person ->
            called = data
        }
        expect(called).toBe(null)
        bus.dispatch(event)
        expect(called).toBe(person)
    }

    // TODO
    fun should_unsubscribe_a_specific_subscriber() {

    }

    // TODO
    fun bus_should_throw_a_descriptive_error_when_an_subscribers_subscribes_to_an_invalid_type() {

    }
}