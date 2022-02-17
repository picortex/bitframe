package bitframe.core.profile.params

import validation.requiredNotBlank
import kotlin.js.JsExport

@JsExport
interface RawChangePasswordParams {
    val previous: String
    val current: String
}

fun RawChangePasswordParams.toValidatedChangePasswordParams() = ChangePasswordParams(
    previous = requiredNotBlank(::previous),
    current = requiredNotBlank(::current)
)