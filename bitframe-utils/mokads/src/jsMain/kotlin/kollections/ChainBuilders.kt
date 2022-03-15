@file:Suppress("NOTHING_TO_INLINE")

package kollections

actual inline fun <E> chainOf(vararg elements: E): Chain<E> = arrayOf(*elements)