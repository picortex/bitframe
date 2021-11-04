package bitframe.server

import bitframe.authentication.AuthenticationDaoProvider
import bitframe.authentication.InMemoryAuthenticationDaoProvider
import bitframe.authentication.spaces.Space
import bitframe.authentication.spaces.SpacesDao
import bitframe.authentication.spaces.SpacesDaoInMemory
import bitframe.authentication.users.Contacts
import bitframe.authentication.users.User
import bitframe.authentication.users.UsersDao
import bitframe.authentication.users.UsersDaoInMemory
import bitframe.server.data.DAOProvider
import kotlin.jvm.JvmOverloads

class InMemoryDaoProvider : DAOProvider, AuthenticationDaoProvider by InMemoryAuthenticationDaoProvider()