package bitframe.dao

import kotlin.reflect.KProperty
import bitframe.dao.Condition.Operator.*

infix fun String.contains(rhs: Any) = Condition(this, Contains, rhs)
infix fun KProperty<*>.contains(rhs: Any) = Condition(name, Contains, rhs)
infix fun String.isEqualTo(rhs: Any) = Condition(this, Equals, rhs)
infix fun KProperty<*>.isEqualTo(rhs: Any) = Condition(name, Equals, rhs)
infix fun String.isLessThan(rhs: Any) = Condition(this, LessThan, rhs)
infix fun String.isGreaterThan(rhs: Any) = Condition(this, GreaterThan, rhs)