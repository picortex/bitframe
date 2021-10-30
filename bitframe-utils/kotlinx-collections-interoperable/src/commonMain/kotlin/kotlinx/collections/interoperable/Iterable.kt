package kotlinx.collections.interoperable

import kotlin.collections.Iterable as KIterable

expect abstract class Iterable<out E>() : KIterable<E>