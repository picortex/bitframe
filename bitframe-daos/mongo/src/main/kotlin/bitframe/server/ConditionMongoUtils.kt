package bitframe.server

import bitframe.core.Condition
import com.mongodb.client.model.Filters
import org.bson.conversions.Bson

fun Condition<*>.toMongoFilter(): Bson = when (operator) {
    Condition.Operator.LessThan -> TODO()
    Condition.Operator.GreaterThan -> TODO()
    Condition.Operator.Equals -> Filters.eq(lhs, rhs)
    Condition.Operator.Contains -> Filters.eq(lhs, Regex(rhs.toString()))
}