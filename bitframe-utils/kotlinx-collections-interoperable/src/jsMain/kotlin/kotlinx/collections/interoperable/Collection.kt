@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package kotlinx.collections.interoperable

import kotlin.collections.Collection as KCollection

actual abstract class Collection<out E> : Iterable<E>(), KCollection<E> {

    init {
        asDynamic()[Symbol.iterator] = ::SymbolIterator
    }

    private fun SymbolIterator() = JsListIterator(iterator())

    open fun toArray(): Array<out E> {
        val array: Array<in Any?> = Array(size) { null }
        forEachWithIndex { e, index -> array[index] = e }
        return array as Array<out E>
    }
}