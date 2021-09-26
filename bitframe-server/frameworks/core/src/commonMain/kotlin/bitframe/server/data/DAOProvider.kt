package bitframe.server.data

import bitframe.authentication.spaces.SpacesDao
import bitframe.authentication.users.UsersDao

interface DAOProvider {
    val users: UsersDao
    val spaces: SpacesDao
}