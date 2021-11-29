@file:JsExport

package validation

import kotlin.js.JsExport

open class ValidationError(
    override val message: String?,
    override val cause: Throwable?
) : IllegalArgumentException(message, cause)