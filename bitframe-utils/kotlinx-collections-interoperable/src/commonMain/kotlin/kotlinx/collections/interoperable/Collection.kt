package kotlinx.collections.interoperable

import kotlin.collections.Collection as KCollection

expect abstract class Collection<out E>() : Iterable<E>, KCollection<E>