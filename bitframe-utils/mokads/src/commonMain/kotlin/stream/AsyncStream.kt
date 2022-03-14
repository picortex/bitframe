package stream

import runner.runner

internal class AsyncStream<D>(
    val block: StreamSender<D>.() -> Unit
) : Stream<D>, StreamSender<D> {

    private val collectors: MutableList<SimpleStreamCollector<D>> = mutableListOf()

    override fun launch(block: () -> Unit) {
        runner<Unit, Unit> { block() }.start(Unit)
    }

    override fun send(value: D) {
        collectors.forEach { it.callable(value) }
    }

    override fun collect(callback: (D) -> Unit): StreamCollector {
        val collector = SimpleStreamCollector(callback, collectors)
        collectors += collector
        runner<Unit, Unit> { block() }.start(Unit)
        return collector
    }
}