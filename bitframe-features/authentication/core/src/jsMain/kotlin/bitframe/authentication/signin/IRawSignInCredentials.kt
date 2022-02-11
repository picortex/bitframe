@file:JsExport

package bitframe.authentication.signin

import kotlin.js.JsExport

actual external interface IRawSignInCredentials {
    actual val email: String?
    actual val phone: String?
    actual val identifier: String?
    actual val password: String
}