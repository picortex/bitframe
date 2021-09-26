package bitframe.server.modules.authentication

import bitframe.authentication.signin.LoginConundrum
import bitframe.authentication.signin.LoginCredentials
import bitframe.authentication.spaces.Space
import bitframe.authentication.spaces.SpacesDao
import bitframe.authentication.users.CreateUserParams
import bitframe.authentication.users.User
import bitframe.authentication.users.UsersDao
import later.Later

interface AuthenticationService {
    val usersDao: UsersDao
    val spacesDao: SpacesDao

    fun createDefaultUserIfNotExist(params: CreateUserParams): Later<User>

    fun registerUser(
        user: RegisterUserParams,
        space: RegisterSpaceParams = RegisterSpaceParams(user.name)
    ): Later<LoginConundrum>

    fun users(): Later<List<User>>
    fun spaces(): Later<List<Space>>
    fun signIn(credentials: LoginCredentials): Later<LoginConundrum>
}