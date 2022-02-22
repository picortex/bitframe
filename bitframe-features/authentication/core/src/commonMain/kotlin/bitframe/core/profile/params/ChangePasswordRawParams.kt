package bitframe.core.profile.params

import validation.requiredNotBlank
import kotlin.js.JsExport

@JsExport
interface ChangePasswordRawParams {
    val previous: String
    val current: String
}

fun ChangePasswordRawParams.toValidatedChangePasswordParams() = ChangePasswordParams(
    previous = requiredNotBlank(::previous),
    current = requiredNotBlank(::current)
)