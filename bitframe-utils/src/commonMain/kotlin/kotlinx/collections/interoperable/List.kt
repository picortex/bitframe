@file:JsExport

package kotlinx.collections.interoperable

import kotlinx.collections.interoperable.serializers.ListSerializer
import kotlinx.serialization.Serializable
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.contract
import kotlin.js.JsExport
import kotlin.collections.List as KList

@Serializable(with = ListSerializer::class)
abstract class List<out E> : KList<E> {
    private var array: Array<out E>? = null
    open fun asArray(): Array<out E> {
        val a = array
        if (contentDeepEquals(a)) return a
        return toArray().also { array = it }
    }

    @OptIn(ExperimentalContracts::class)
    private fun contentDeepEquals(array: Array<out E>?): Boolean {
        contract {
            returns(true) implies (array != null)
        }
        if (array == null) return false
        if (array.size == size) {
            forEachIndexed { index, _ ->
                if (this[index] != array[index]) return false
            }
            return true
        } else {
            return false
        }
    }

    open fun toArray(): Array<out E> {
        val array: Array<in Any?> = Array(size) { null }
        forEachIndexed { index, e -> array[index] = e }
        return array as Array<out E>
    }
}