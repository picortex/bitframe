package bitframe.core

import kotlinx.serialization.KSerializer

//import kotlinx.serialization.json.Json
//import kotlinx.serialization.mapper.Mapper

@Deprecated("in favour of its quivalent in bitframe.dao")
fun <T> Condition<Any?>.asPredicate(
    serializer: KSerializer<T>
) = { it: T ->
//    val json = Json.encodeToString(serializer, it)
//    val map = Mapper.decodeFromString(json)
//    when (operator) {
//        Condition.Operator.LessThan -> TODO()
//        Condition.Operator.GreaterThan -> TODO()
//        Condition.Operator.Equals -> map[lhs].toString().contentEquals(rhs.toString(), ignoreCase = true)
//        Condition.Operator.Contains -> map[lhs].toString().contains(rhs.toString(), ignoreCase = true)
//    }
    TODO()
}