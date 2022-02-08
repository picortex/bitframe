@file:JsExport

package bitframe.actors.users

import bitframe.actors.spaces.Space
import identifier.Name
import kotlinx.collections.interoperable.listOf
import kotlinx.serialization.Serializable
import kotlin.js.JsExport

fun RegisterUserParams.toUserInput() = User(
    name = userName,
    tag = Name(userName).first,
    contacts = listOf(),
    spaces = listOf()
)

fun RegisterUserParams.toSpaceInput() = Space(
    name = spaceName,
    scope = spaceScope,
    type = spaceType
)