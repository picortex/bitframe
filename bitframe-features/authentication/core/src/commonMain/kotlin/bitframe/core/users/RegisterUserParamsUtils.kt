package bitframe.core.users

import bitframe.core.Space
import bitframe.core.User
import identifier.Name

fun RegisterUserParams.toUserInput() = User(
    name = userName,
    tag = Name(userName).first
)

fun RegisterUserParams.toSpaceInput() = Space(
    name = spaceName,
    type = spaceType
)