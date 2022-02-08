package bitframe.authentication.spaces

import bitframe.actors.spaces.Space
import bitframe.authentication.spaces.CreateSpaceParams

fun CreateSpaceParams.toSpace(uid: String) = Space(
    uid = uid,
    name = name,
    scope = scope,
    type = type
)