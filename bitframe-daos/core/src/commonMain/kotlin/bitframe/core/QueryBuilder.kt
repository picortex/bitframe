package bitframe.core

import kotlinx.collections.interoperable.listOf

fun <V> find(condition: Condition<V>) = Query(listOf(condition))

fun <V> filter(condition: Condition<V>) = Query(listOf(condition))