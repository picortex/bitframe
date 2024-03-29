package validation

import kotlin.reflect.KProperty0

inline fun <T> required(
    property: KProperty0<T>
): T = property.get() ?: throw MissingFieldException(property.name)

inline fun requiredNotBlank(
    property: KProperty0<String?>
): String {
    val nonNullValue = property.get() ?: throw MissingFieldException(property.name)
    if (nonNullValue.isBlank()) throw BlankFieldException(property.name)
    return nonNullValue
}

inline fun requiredPositiveDouble(
    property: KProperty0<Any?>
): Double {
    val nonNullValue = property.get() ?: throw MissingFieldException(property.name)
    if (nonNullValue.toString().isBlank()) throw BlankFieldException(property.name)
    return nonNullValue.toString().toDouble()
}

inline fun String.requiredNotBlank(fieldName: String) = takeIf { it.isNotBlank() } ?: throw BlankFieldException(fieldName)

inline fun <T : Number?> requiredPositive(
    property: KProperty0<T>
): T {
    val nonNullValue = required(property) ?: throw MissingFieldException(property.name)
    if (nonNullValue.toDouble() <= 0.0) throw NotPositiveException(property.name)
    return nonNullValue
}