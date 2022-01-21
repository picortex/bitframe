package unit

import events.Event
import events.EventBus
import events.InMemoryEventBus
import events.SubscriptionException
import expect.expect
import expect.expectFailure
import expect.toBe
import kotlinx.coroutines.test.runTest
import kotlin.test.Ignore
import kotlin.test.Test

class EventBusTest {
    val bus: EventBus = InMemoryEventBus()

    class TestEvent<out T>(data: T) : Event<T>(data, "test_event")

    @Test
    fun should_be_able_to_dispatch_a_event() {
        val event = TestEvent(23)
        bus.dispatch(event)
    }

    @Test
    fun should_be_able_to_subscribe_to_events() {
        val event = TestEvent(7)
        var receivedData: Int? = null
        bus.subscribe("test_event") { data: Int ->
            receivedData = data
        }
        bus.dispatch(event)
        expect(receivedData).toBe(7)
    }

    @Test
    fun subscribers_should_listen_only_to_the_event_id_the_specify() {
        val event1 = Event(1, "test-event-1")
        val event2 = Event(2, "test-event-2")
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
        val event = Event(person, "created_person")
        var called: Person? = null
        bus.subscribe("created_person") { data: Person ->
            called = data
        }
        expect(called).toBe(null)
        bus.dispatch(event)
        expect(called).toBe(person)
    }

    @Test
    fun should_unsubscribe_a_specific_subscriber() {
        fun send(int: Int) {
            bus.dispatch(Event(int, "test-event"))
        }

        var receivedData1: Int? = null
        var receivedData2: Int? = null
        val subscriber1 = bus.subscribe("test-event") { data: Int ->
            receivedData1 = data
        }
        val subscriber2 = bus.subscribe("test-event") { data: Int ->
            receivedData2 = data
        }
        send(1)
        expect(receivedData1).toBe(1)
        expect(receivedData2).toBe(1)
        send(2)
        expect(receivedData1).toBe(2)
        expect(receivedData2).toBe(2)
//        subscriber2.unsubscribe()
        subscriber2.unsubscribe()
        send(3)
        expect(receivedData1).toBe(3)
        expect(receivedData2).toBe(2)
    }

    @Test
    @Ignore
    fun bus_should_throw_a_descriptive_error_when_an_subscribers_subscribes_to_an_invalid_type() = runTest {
        val err = expectFailure {
            val event: Event<Int> = TestEvent(22)
            val subscriber = bus.subscribe("test_event") { data: Event<Int> ->
                println("data: ${data.topic}")
            }
            bus.dispatch(event)
            subscriber.unsubscribe()
        }
        expect(err).toBe<SubscriptionException>()
        expect(err.message).toBe("Subscriber of topic test_event did not subscribe to get data of type Int")
    }
}