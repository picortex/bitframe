package bitframe.authentication.users

import bitframe.authentication.spaces.RegisterSpaceParams
import kotlinx.serialization.Serializable
import kotlin.js.JsExport

fun RegisterUserParams.toRegisterSpaceParams() = RegisterSpaceParams(name)