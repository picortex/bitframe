package bitframe.core

import kollections.List
import kollections.toIList
import kotlin.reflect.KProperty

@Deprecated("in favour of its quivalent in bitframe.dao")
data class Query(
    val statements: List<QueryStatement>
) {
    fun limit(value: Int) = Query(
        statements = (statements + LimitStatement(value)).toIList()
    )

    fun where(condition: Condition<Any?>) = Query(
        statements = (statements + condition).toIList()
    )

    fun sortBy(property: String) = Query(
        statements = (statements + SortStatement(property)).toIList()
    )

    fun sortBy(property: KProperty<Any?>) = sortBy(property.name)
}