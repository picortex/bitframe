package bitframe.core.profile.params

import validation.required
import kotlin.js.JsExport

@JsExport
interface RawChangePasswordParams {
    val previous: String
    val current: String
}

fun RawChangePasswordParams.toValidatedChangePasswordParams() = ChangePasswordParams(
    previous = required(::previous),
    current = required(::current)
)