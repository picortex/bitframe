@file:JsExport

package validation

import kotlin.js.JsExport

sealed class Validation<out T> {
    data class Valid<out T>(val value: T) : Validation<T>()

    data class Invalid(val cause: ValidationError) : Validation<Nothing>()

    fun throwIfInvalid() {
        if (this is Invalid) throw cause
    }

    fun getOrThrow(): T = when (this) {
        is Valid -> value
        is Invalid -> throw cause
    }

    fun getOrNull(): T? = when (this) {
        is Valid -> value
        is Invalid -> null
    }
}