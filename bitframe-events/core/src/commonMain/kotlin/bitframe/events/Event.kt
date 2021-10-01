package bitframe.events

open class Event<out D>(
    val id: String,
    val data: D
)