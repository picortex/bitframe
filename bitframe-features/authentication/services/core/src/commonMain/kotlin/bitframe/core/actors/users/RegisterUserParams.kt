@file:JsExport

package bitframe.core.actors.users

import bitframe.core.actors.spaces.SpaceType
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
)