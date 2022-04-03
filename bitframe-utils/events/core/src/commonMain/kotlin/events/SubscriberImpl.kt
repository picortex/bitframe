package events

class SubscriberImpl<D>(
    topic: String,
    private val callback: EventCallback<D>,
    private val container: MutableList<Subscriber<Any>>
) : Subscriber<D>(topic) {
    override fun invoke(data: D) = try {
        callback(data)
    } catch (err: ClassCastException) {
        throw SubscriptionException("Subscriber of topic $topic did not subscribe to get data of type ${data!!::class.simpleName}", err)
    }

    override fun unsubscribe() {
        container.remove(this as Subscriber<Any>)
    }
}