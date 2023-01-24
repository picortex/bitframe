package bitframe

import bitframe.dao.Condition
import bitframe.dao.LimitStatement
import bitframe.dao.Query
import bitframe.dao.SortStatement
import com.mongodb.client.MongoCollection
import com.mongodb.client.model.Filters.eq

internal fun <T : Any> MongoCollection<T>.execute(query: Query): List<T> {
    var result = find(query.toMongoFilter())
    query.statements.forEach { statement ->
        result = when (statement) {
            is Condition<Any?> -> result
            is LimitStatement -> result.limit(statement.value)
            is SortStatement -> result.sort(eq(statement.property))
        }
    }
    return result.toList()
}