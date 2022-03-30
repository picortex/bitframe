@file:JsExport

package validation

import kotlin.js.JsExport

class Invalid(val cause: ValidationException) : Validation<Nothing>