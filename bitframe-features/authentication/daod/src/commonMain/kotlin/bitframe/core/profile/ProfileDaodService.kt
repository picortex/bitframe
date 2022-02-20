package bitframe.core.profile

import bitframe.core.*
import bitframe.core.profile.params.ChangePasswordParams
import bitframe.core.users.UserCredentials
import later.Later
import later.await
import later.later

open class ProfileDaodService(
    open val config: DaodServiceConfig
) : ProfileService {
    val scope get() = config.scope
    val usersCredentialsDao by lazy { config.daoFactory.get<UserCredentials>() }
    override fun changePassword(rb: RequestBody.Authorized<ChangePasswordParams>): Later<ChangePasswordParams> = scope.later {
        val userId = rb.session.user.uid
        val data = rb.data
        val query = usersCredentialsDao.all(UserCredentials::userId isEqualTo userId)
        val input = query.await().firstOrNull() ?: error("Can't updated credentials of a non existing users")
        if (input.credential != data.previous) error("Old password did not match")
        val param = input.copy(credential = data.current)
        usersCredentialsDao.update(param).await()
        data
    }
}