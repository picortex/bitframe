package bitframe.core.signin

import kotlin.js.JsExport

/**
 * This accepts a raw identifier/password credentials.
 *
 * It is built to allow dynamic usage on platforms like javascript
 * It can be uses as follow
 * #### Usage
 * ```
 * const credentials = {
 *      email: "test@email.com",
 *      password: "password"
 * }
 * // or
 * const credentials = {
 *      phone: "255752748674",
 *      password: "password"
 * }
 * // or. If you want to be universal
 * const credentials = {
 *      identifier: "test@email.com" // even phone will work
 *      password: "password"
 * }
 * ```
 */
@JsExport
interface RawSignInCredentials {
    val phone: String?
    val email: String?
    val identifier: String?
    val password: String
}