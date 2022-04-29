package emitter

typealias ErrorRecoverer<T> = (EmitterBuilder<T>, Throwable) -> Unit