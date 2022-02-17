package validation

import kotlin.reflect.KProperty0

inline fun <T> required(
    property: KProperty0<T>
): T = property.get() ?: throw ValidationError("Property ${property.name} is required", null)

inline fun requiredNotBlank(
    property: KProperty0<String?>
): String {
    val nonNullValue = property.get() ?: throw ValidationError("Property ${property.name} is required", null)
    if (nonNullValue.isBlank()) throw ValidationError("Property ${property.name} must not be empty/blank", null)
    return nonNullValue
}