package validation

import kotlin.reflect.KProperty0

inline fun <T> required(
    property: KProperty0<T>
): T = property.get() ?: throw ValidationError("Property ${property.name} is required", null)