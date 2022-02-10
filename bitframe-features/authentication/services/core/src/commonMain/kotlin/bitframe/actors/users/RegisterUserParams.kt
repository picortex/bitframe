@file:JsExport

package bitframe.actors.users

import bitframe.actors.spaces.SpaceType
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