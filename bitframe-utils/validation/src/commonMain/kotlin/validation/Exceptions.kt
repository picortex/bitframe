@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package validation

import kotlin.js.JsExport

open class ValidationException(
    override val message: String?,
    override val cause: Throwable?
) : IllegalArgumentException(message, cause)

open class BlankFieldException(
    val fieldName: String,
) : ValidationException("Field $fieldName is required and must not be blank", null)

open class MissingFieldException(
    val fieldName: String
) : ValidationException("Field $fieldName is required but was not found", null)

open class NotPositiveException(
    val fieldName: String
) : ValidationException("Field $fieldName must be positive", null)