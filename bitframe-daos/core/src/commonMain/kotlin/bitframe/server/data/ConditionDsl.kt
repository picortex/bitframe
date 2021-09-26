package bitframe.server.data

import bitframe.server.data.Condition.Operator.*

infix fun String.contains(rhs: Any) = Condition(this, Contains, rhs)
infix fun String.isEqualTo(rhs: Any) = Condition(this, Equals, rhs)
infix fun String.isLessThan(rhs: Any) = Condition(this, LessThan, rhs)
infix fun String.isGreaterThan(rhs: Any) = Condition(this, GreaterThan, rhs)