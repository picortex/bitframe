package bitframe.dao

open class Condition<out V>(
    val lhs: String,
    val operator: Operator = Operator.Equals,
    val rhs: V
) : QueryStatement {
    enum class Operator {
        LessThan,
        GreaterThan,
        Equals,
        Contains
    }

    override fun toString(): String {
        val middle = if (operator == Operator.Contains) "contains" else "is${operator.name}"
        return "Condition($lhs $middle $rhs)"
    }
}