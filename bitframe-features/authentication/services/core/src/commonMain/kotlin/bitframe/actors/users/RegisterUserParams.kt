@file:JsExport

package bitframe.actors.users

import kotlinx.serialization.Serializable
import kotlin.js.JsExport

@Serializable
data class RegisterUserParams(
    val userName: String,
    val userIdentifier: String,
    val userPassword: String,
    val spaceName: String,
    val spaceType: String,
    val spaceScope: String
)