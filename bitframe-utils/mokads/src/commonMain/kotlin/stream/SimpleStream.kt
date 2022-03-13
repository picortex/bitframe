package stream

internal class SimpleStream<D>(
    val block: StreamSender<D>.() -> Unit
) : Stream<D>, StreamSender<D> {

    private val collectors: MutableList<SimpleStreamCollector<D>> = mutableListOf()

    override fun launch(block: () -> Unit) {
        block()
    }

    override fun send(value: D) {
        collectors.forEach { it.callable(value) }
    }

    override fun collect(callback: (D) -> Unit): StreamCollector {
        val collector = SimpleStreamCollector<D>(callback, collectors)
        collectors += collector
        block()
        return collector
    }
}