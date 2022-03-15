@file:Suppress("EXTENSION_SHADOWED_BY_MEMBER", "NOTHING_TO_INLINE")

package kollections

import kotlin.collections.map as kMap

actual inline val Chain<*>.size: Int get() = size
actual inline operator fun <E> Chain<E>.get(index: Int): E = get(index)
actual inline operator fun <E> Chain<E>.iterator(): Iterator<E> = iterator()
actual inline operator fun <E> Chain<E>.set(index: Int, value: E) = set(index, value)
actual inline fun <E> Chain<E>.toList(): List<E> = toMutableList()

actual inline fun <E, S> Chain<E>.map(transform: (E) -> S): Chain<S> = kMap(transform).toTypedArray()

fun <E> Chain<E>.equals(other: Any?): Boolean = when (other) {
    is Chain<*> -> size == other.size
    else -> false
}