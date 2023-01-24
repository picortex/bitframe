package bitframe.dao

import kollections.iListOf

fun <V> find(condition: Condition<V>) = Query(iListOf(condition))

fun <V> filter(condition: Condition<V>) = Query(iListOf(condition))