@file:JsExport

package bitframe.authentication.signin

import kotlin.js.JsExport

actual external interface IRawSignInCredentials {
    actual var email: String
    actual var password: String
}