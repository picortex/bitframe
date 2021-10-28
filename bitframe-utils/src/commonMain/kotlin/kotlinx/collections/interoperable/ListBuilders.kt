package kotlinx.collections.interoperable

inline fun <E> mutableListOf(): MutableList<E> = ArrayList(arrayListOf())

inline fun <E> mutableListOf(vararg elements: E): MutableList<E> = ArrayList(arrayListOf(*elements))

fun <E> listOf(): List<E> = EmptyList

fun emptyList(): List<Nothing> = EmptyList

inline fun <E> listOf(vararg elements: E): List<E> = mutableListOf(*elements)