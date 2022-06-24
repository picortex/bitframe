package bitframe

import bitframe.dao.Condition
import bitframe.dao.LimitStatement
import bitframe.dao.Query
import bitframe.dao.SortStatement
import com.mongodb.client.model.Filters.eq
import org.litote.kmongo.coroutine.CoroutineCollection
import org.litote.kmongo.coroutine.CoroutineFindPublisher

internal fun <T : Any> CoroutineCollection<T>.execute(query: Query): CoroutineFindPublisher<T> {
    var publisher = find(query.toMongoFilter())
    query.statements.forEach { statement ->
        publisher = when (statement) {
            is Condition<*> -> publisher
            is LimitStatement -> publisher.limit(statement.value)
            is SortStatement -> publisher.sort(eq(statement.property))
        }
    }
    return publisher
}