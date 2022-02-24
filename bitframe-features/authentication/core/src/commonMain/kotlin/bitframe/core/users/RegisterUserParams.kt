@file:JsExport

package bitframe.core.users

import bitframe.core.SpaceScope
import bitframe.core.SpaceType
import bitframe.core.UserType
import kotlinx.serialization.Serializable
import kotlin.js.JsExport

@Serializable
data class RegisterUserParams(
    val userName: String,
    val userIdentifier: String,
    val userPassword: String,
    val userType: UserType,
    val spaceName: String,
    val spaceType: SpaceType,
    val spaceScope: SpaceScope
)