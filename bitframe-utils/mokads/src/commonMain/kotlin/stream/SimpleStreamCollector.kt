package stream

import kotlin.jvm.JvmSynthetic

class SimpleStreamCollector<in D> internal constructor(
    @JvmSynthetic
    internal val callable: (D) -> Unit,
    private val container: MutableList<SimpleStreamCollector<D>>
) : StreamCollector {
    override fun stop() = container.remove(this)
}