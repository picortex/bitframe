package bitframe.core

import kotlinx.collections.interoperable.listOf

@Deprecated("in favour of its quivalent in bitframe.dao")
fun <V> find(condition: Condition<V>) = Query(listOf(condition))

@Deprecated("in favour of its quivalent in bitframe.dao")

fun <V> filter(condition: Condition<V>) = Query(listOf(condition))