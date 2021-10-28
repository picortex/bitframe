package kotlinx.collections.interoperable

import kotlinx.collections.interoperable.serializers.ArrayListSerializer
import kotlinx.serialization.Serializable
import kotlin.js.JsExport
import kotlin.js.JsName
import kotlin.collections.ArrayList as KArrayList
import kotlin.collections.MutableList as KMutableList

@Serializable(with = ArrayListSerializer::class)
class ArrayList<E>(private val list: KArrayList<E>) : MutableList<E>(), KMutableList<E> by list {
    @JsName("FromCollection")
    constructor(collection: Collection<E>) : this(KArrayList(collection))

    override fun toString(): String = list.toString()
}