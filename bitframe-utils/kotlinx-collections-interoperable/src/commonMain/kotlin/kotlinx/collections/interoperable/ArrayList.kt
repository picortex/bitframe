package kotlinx.collections.interoperable

import kotlinx.collections.interoperable.serializers.ArrayListSerializer
import kotlinx.serialization.Serializable
import kotlin.js.JsExport
import kotlin.js.JsName
import kotlin.collections.ArrayList as KArrayList
import kotlin.collections.Collection as KCollection
import kotlin.collections.MutableList as KMutableList

@Serializable(with = ArrayListSerializer::class)
class ArrayList<E>(private val list: KArrayList<E>) : MutableList<E>(), KMutableList<E> by list {
    @JsName("FromCollection")
    constructor(collection: KCollection<E>) : this(KArrayList(collection))

    override fun subList(
        fromIndex: Int,
        toIndex: Int
    ): ArrayList<E> = list.subList(fromIndex, toIndex).toInteroperableArrayList()

    override fun toString(): String = list.toString()

    override fun hashCode(): Int = list.hashCode()

    override fun equals(other: Any?): Boolean = list == other
}