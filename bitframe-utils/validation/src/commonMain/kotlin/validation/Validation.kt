@file:JsExport

package validation

import kotlin.js.JsExport

sealed class Validation<out T> {

    fun throwIfInvalid() {
        if (this is Invalid) throw cause
    }

    fun getOrDefault(default: @UnsafeVariance T): T = when (this) {
        is Valid -> value
        is Invalid -> default
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