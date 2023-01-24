package bitframe.core

import kollections.iListOf

@Deprecated("in favour of its quivalent in bitframe.dao")
fun <V> find(condition: Condition<V>) = Query(iListOf(condition))

@Deprecated("in favour of its quivalent in bitframe.dao")

fun <V> filter(condition: Condition<V>) = Query(iListOf(condition))