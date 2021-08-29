package bitframe.server.data

class Condition<K, V>(
    val lhs: K,
    val operator: Operator = Operator.Equals,
    val rhs: V
) {
    enum class Operator {
        LessThan,
        GreaterThan,
        Equals
    }
}

fun testCondition() {
    val where = Condition("username", Condition.Operator.Equals, "anderson")
    sql(where)
}

fun sql(condition: Condition<*, *>) {
    // do things here
}

fun mongo(condition: Condition<*, *>) {
    // do things
}