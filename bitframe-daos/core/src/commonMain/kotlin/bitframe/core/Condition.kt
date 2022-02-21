package bitframe.core

open class Condition<out V>(
    val lhs: String,
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