package bitframe.core

import bitframe.core.Condition.Operator.*
import kotlin.reflect.KProperty

@Deprecated("in favour of its quivalent in bitframe.dao")
infix fun String.contains(rhs: Any) = Condition(this, Contains, rhs)

@Deprecated("in favour of its quivalent in bitframe.dao")
infix fun KProperty<*>.contains(rhs: Any) = Condition(name, Contains, rhs)

@Deprecated("in favour of its quivalent in bitframe.dao")
infix fun String.isEqualTo(rhs: Any) = Condition(this, Equals, rhs)

@Deprecated("in favour of its quivalent in bitframe.dao")
infix fun KProperty<*>.isEqualTo(rhs: Any) = Condition(name, Equals, rhs)

@Deprecated("in favour of its quivalent in bitframe.dao")
infix fun String.isLessThan(rhs: Any) = Condition(this, LessThan, rhs)

@Deprecated("in favour of its quivalent in bitframe.dao")
infix fun String.isGreaterThan(rhs: Any) = Condition(this, GreaterThan, rhs)