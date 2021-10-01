package bitframe.events

interface Subscriber<D> {
    val eventId: String
    fun invoke(data: D)
    fun unsubscribe()
}