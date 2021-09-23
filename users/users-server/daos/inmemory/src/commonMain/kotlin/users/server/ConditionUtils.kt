package users.server

import bitframe.server.data.Condition
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.mapper.Mapper

fun Condition<String, Any?>.apply(map: Map<String, Any?>): Boolean = when (operator) {
    Condition.Operator.LessThan -> TODO()
    Condition.Operator.GreaterThan -> TODO()
    Condition.Operator.Equals -> TODO()
    Condition.Operator.Contains -> map[lhs].toString().contains(rhs.toString())
}

fun <T> Collection<T>.matching(
    condition: Condition<String, *>,
    serializer: KSerializer<T>
): Collection<T> = filter {
    val json = Json.encodeToString(serializer, it)
    val map = Mapper.decodeFromString(json)
    when (condition.operator) {
        Condition.Operator.LessThan -> TODO()
        Condition.Operator.GreaterThan -> TODO()
        Condition.Operator.Equals -> map[condition.lhs].toString().contentEquals(condition.rhs.toString(), ignoreCase = true)
        Condition.Operator.Contains -> map[condition.lhs].toString().contains(condition.rhs.toString())
    }
}