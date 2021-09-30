package bitframe.daos.conditions

open class Condition<out K, out V>(
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