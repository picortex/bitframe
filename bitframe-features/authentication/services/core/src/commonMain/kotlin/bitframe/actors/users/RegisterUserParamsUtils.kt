package bitframe.actors.users

import bitframe.authentication.spaces.CreateSpaceParams

fun RegisterUserParams.toCreateSpaceParams() = CreateSpaceParams(name)