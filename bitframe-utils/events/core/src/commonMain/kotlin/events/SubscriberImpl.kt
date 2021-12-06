package events

class SubscriberImpl<D>(
    eventId: String,
    private val callback: (D) -> Unit,
    private val container: MutableList<Subscriber<Any>>
) : Subscriber<D>(eventId) {
    override fun invoke(data: D) {
        callback(data)
    }

    override fun unsubscribe() {
        container.remove(this as Subscriber<Any>)
    }
}