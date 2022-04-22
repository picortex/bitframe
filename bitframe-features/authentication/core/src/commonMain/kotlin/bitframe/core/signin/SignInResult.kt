@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package bitframe.core.signin

import bitframe.core.Space
import bitframe.core.User
import kotlinx.collections.interoperable.List
import kotlinx.serialization.Serializable
import kotlin.js.JsExport

@Serializable
data class SignInResult(
    val user: User,
    val spaces: List<Space>
)