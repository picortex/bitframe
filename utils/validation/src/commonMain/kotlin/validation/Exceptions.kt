@file:JsExport

package validation

import kotlin.js.JsExport
import kotlin.js.JsName

open class ValidationException(
    override val message: String?,
    override val cause: Throwable?
) : IllegalArgumentException(message, cause) {
    @JsName("withMessage")
    constructor(message: String?) : this(message, null)
}

open class BlankFieldException(
    val fieldName: String,
) : ValidationException("Field `$fieldName` is required and must not be blank", null)

open class MissingFieldException(
    val fieldName: String
) : ValidationException("Field `$fieldName` is required but was not found", null)

open class NotPositiveException(
    val fieldName: String
) : ValidationException("Field `$fieldName` must be positive", null)