package bitframe.daos.conditions

import bitframe.daos.conditions.Condition.Operator.*

infix fun String.contains(rhs: Any) = Condition(this, Contains, rhs)
infix fun String.isEqualTo(rhs: Any) = Condition(this, Equals, rhs)
infix fun String.isLessThan(rhs: Any) = Condition(this, LessThan, rhs)
infix fun String.isGreaterThan(rhs: Any) = Condition(this, GreaterThan, rhs)