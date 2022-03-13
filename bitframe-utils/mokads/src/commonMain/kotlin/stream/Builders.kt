package stream

import kotlin.experimental.ExperimentalTypeInference

@OptIn(ExperimentalTypeInference::class)
fun <D> stream(@BuilderInference builder: StreamSender<D>.() -> Unit): Stream<D> = simpleStream(builder)

@OptIn(ExperimentalTypeInference::class)
internal fun <D> simpleStream(@BuilderInference builder: StreamSender<D>.() -> Unit): Stream<D> = SimpleStream(builder)