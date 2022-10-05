package bitframe

import bitframe.dao.Condition
import bitframe.dao.Query
import com.mongodb.client.model.Filters
import org.bson.conversions.Bson

fun Query.toMongoFilter(): Bson {
    val conditions = statements.filterIsInstance<Condition<*>>()
    return Filters.and(conditions.map { it.toMongoFilter() })
}