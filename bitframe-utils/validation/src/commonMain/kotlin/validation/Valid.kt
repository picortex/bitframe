@file:JsExport

package validation

import kotlin.js.JsExport

class Valid<out T>(val value: T) : Validation<T>