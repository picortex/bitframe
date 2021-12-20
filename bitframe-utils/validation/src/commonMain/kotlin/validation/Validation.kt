@file:JsExport
@file:Suppress("WRONG_EXPORTED_DECLARATION")

package validation

import kotlin.js.JsExport
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

sealed interface Validation<out T> {

    fun throwIfInvalid() {
        if (this is Invalid) throw cause
    }

    fun getOrDefault(defaultValue: @UnsafeVariance T): T = when (this) {
        is Valid -> value
        is Invalid -> defaultValue
    }

    fun getOrThrow(): T = when (this) {
        is Valid -> value
        is Invalid -> throw cause
    }

    fun getOrNull(): T? = when (this) {
        is Valid -> value
        is Invalid -> null
    }

    operator fun getValue(thisRef: Nothing?, property: KProperty<*>): T = getOrThrow()
}