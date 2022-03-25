package validation

import kotlin.reflect.KProperty0

class RequirePropertyError(
    val property: KProperty0<*>
) : ValidationError("Property ${property.name} is required", null)

inline fun <T> required(
    property: KProperty0<T>
): T = property.get() ?: throw RequirePropertyError(property)

inline fun requiredNotBlank(
    property: KProperty0<String?>
): String {
    val nonNullValue = property.get() ?: throw RequirePropertyError(property)
    if (nonNullValue.isBlank()) throw ValidationError("Property ${property.name} must not be empty/blank", null)
    return nonNullValue
}

inline fun <T : Number?> requiredPositive(
    property: KProperty0<T>
): T {
    val nonNullValue = required(property) ?: throw RequirePropertyError(property)
    if (nonNullValue.toDouble() <= 0.0) throw ValidationError("Property ${property.name} must be positive", null)
    return nonNullValue
}