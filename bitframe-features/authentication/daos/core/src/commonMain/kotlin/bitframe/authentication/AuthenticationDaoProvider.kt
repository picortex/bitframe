package bitframe.authentication

import bitframe.authentication.spaces.SpacesDao
import bitframe.authentication.users.UsersDao

interface AuthenticationDaoProvider {
    val users: UsersDao
    val spaces: SpacesDao
}