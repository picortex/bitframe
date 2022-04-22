@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package response

import kotlin.js.JsExport
import kotlin.js.JsName

class ResponseException(
    override val message: String,
    val reason: String,
    val type: String,
    val stackTrace: String = ""
) : RuntimeException(message) {
    @JsName("_ignore_fromError")
    constructor(error: Error) : this(
        message = error.message,
        reason = error.cause,
        type = error.type,
        stackTrace = error.stackTrace
    )
}