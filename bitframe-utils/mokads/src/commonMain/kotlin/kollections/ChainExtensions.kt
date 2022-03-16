package kollections

expect val Chain<*>.size: Int
expect inline operator fun <E> Chain<E>.get(index: Int): E
expect inline operator fun <E> Chain<E>.iterator(): Iterator<E>
expect inline operator fun <E> Chain<E>.set(index: Int, value: E)
expect inline fun <E> Chain<E>.toList(): List<E>

expect inline fun <E, S> Chain<E>.map(transform: (E) -> S): Chain<S>