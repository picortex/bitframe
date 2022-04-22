@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor.core.signup

import bitframe.core.App
import bitframe.core.Space
import bitframe.core.User
import kotlinx.serialization.Serializable
import kotlin.js.JsExport

@Serializable
data class SignUpResult(
    val app: App,
    val space: Space,
    val user: User
)