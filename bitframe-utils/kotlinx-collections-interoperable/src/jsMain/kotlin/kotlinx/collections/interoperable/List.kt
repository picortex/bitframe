@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package kotlinx.collections.interoperable

import kotlinx.collections.interoperable.serializers.ListSerializer
import kotlinx.serialization.Serializable
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.contract
import kotlin.js.JsExport
import kotlin.collections.List as KList

@Serializable(with = ListSerializer::class)
actual abstract class List<out E> : KList<E> {
    fun contentDeepEquals(array: Array<*>?): Boolean {
        if (array == null) return false
        if (array.size == size) {
            var equals = true
            forEachIndexed { _, index ->
                equals = this[index] == array[index]
                if (!equals) return@forEachIndexed
            }
            return equals
        } else {
            return false
        }
    }

    open fun toArray(): Array<out E> {
        val array: Array<in Any?> = Array(size) { null }
        forEachIndexed { e, index -> array[index] = e }
        return array as Array<out E>
    }

    fun forEach(lambda: (item: E) -> Unit) {
        for (item in this) lambda(item)
    }

    fun forEachIndexed(lambda: (item: E, index: Int) -> Unit) {
        for (item in this) lambda(item, indexOf(item))
    }

    fun <O> map(transform: (item: E) -> O): List<O> {
        val l = mutableListOf<O>()
        for (item in this) l.add(transform(item))
        return l
    }

    fun <O> mapIndexed(transform: (item: E, index: Int) -> O): List<O> {
        val l = mutableListOf<O>()
        for (item in this) l.add(transform(item, indexOf(item)))
        return l
    }
}