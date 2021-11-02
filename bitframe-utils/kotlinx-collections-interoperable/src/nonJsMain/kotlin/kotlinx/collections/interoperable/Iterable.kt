package kotlinx.collections.interoperable

import kotlin.collections.Iterable as KIterable

actual abstract class Iterable<out E> : KIterable<E>