package bitframe.server

import bitframe.authentication.AuthenticationDaoProvider
import bitframe.authentication.spaces.SpacesDao
import bitframe.authentication.users.UsersDao

interface BitframeDaoProvider : AuthenticationDaoProvider {
    companion object {
        operator fun invoke(
            users: UsersDao,
            spaces: SpacesDao
        ) = object : BitframeDaoProvider {
            override val users: UsersDao = users
            override val spaces: SpacesDao = spaces
        }
    }
}