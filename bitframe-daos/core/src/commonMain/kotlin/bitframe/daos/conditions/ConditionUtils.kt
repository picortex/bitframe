package bitframe.daos.conditions

import kotlinx.serialization.KSerializer
import kotlinx.serialization.json.Json
import kotlinx.serialization.mapper.Mapper

fun <T> Collection<T>.matching(
    condition: Condition<String, *>,
    serializer: KSerializer<T>
): List<T> = filter {
    val json = Json.encodeToString(serializer, it)
    val map = Mapper.decodeFromString(json)
    when (condition.operator) {
        Condition.Operator.LessThan -> TODO()
        Condition.Operator.GreaterThan -> TODO()
        Condition.Operator.Equals -> map[condition.lhs].toString().contentEquals(condition.rhs.toString(), ignoreCase = true)
        Condition.Operator.Contains -> map[condition.lhs].toString().contains(condition.rhs.toString(), ignoreCase = true)
    }
}