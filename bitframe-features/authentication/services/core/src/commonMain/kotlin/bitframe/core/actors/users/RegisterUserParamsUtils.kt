@file:JsExport

package bitframe.core.actors.users

import bitframe.core.User
import bitframe.core.actors.spaces.Space
import identifier.Name
import kotlin.js.JsExport

fun RegisterUserParams.toUserInput() = User(
    name = userName,
    tag = Name(userName).first
)

fun RegisterUserParams.toSpaceInput() = Space(
    name = spaceName,
    type = spaceType
)