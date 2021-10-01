package bitframe.events

class SubscriberImpl<D>(override val eventId: String, val callback: (D) -> Unit) : Subscriber<D> {
    override fun invoke(data: D) {
        callback(data)
    }

    override fun unsubscribe() {
        TODO("Not yet implemented")
    }
}