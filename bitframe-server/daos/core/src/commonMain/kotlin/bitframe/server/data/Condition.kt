package bitframe.server.data

open class Condition<K, V>(
    val lhs: K,
    val operator: Operator = Operator.Equals,
    val rhs: V
) {
    enum class Operator {
        LessThan,
        GreaterThan,
        Equals,
        Contains
    }
}