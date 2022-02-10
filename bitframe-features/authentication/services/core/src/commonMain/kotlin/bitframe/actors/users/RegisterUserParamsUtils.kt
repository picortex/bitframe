@file:JsExport

package bitframe.actors.users

import bitframe.actors.spaces.Space
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