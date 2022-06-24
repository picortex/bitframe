package bitframe.core

import kotlinx.collections.interoperable.List
import kotlinx.collections.interoperable.toInteroperableList
import kotlin.reflect.KProperty

@Deprecated("in favour of its quivalent in bitframe.dao")
data class Query(
    val statements: List<QueryStatement>
) {
    fun limit(value: Int) = Query(
        statements = (statements + LimitStatement(value)).toInteroperableList()
    )

    fun where(condition: Condition<*>) = Query(
        statements = (statements + condition).toInteroperableList()
    )

    fun sortBy(property: String) = Query(
        statements = (statements + SortStatement(property)).toInteroperableList()
    )

    fun sortBy(property: KProperty<*>) = sortBy(property.name)
}