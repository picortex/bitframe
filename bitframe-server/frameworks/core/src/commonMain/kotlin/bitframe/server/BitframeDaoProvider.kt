package bitframe.server

import bitframe.authentication.AuthenticationDaoProvider
import bitframe.authentication.spaces.SpacesDao
import bitframe.authentication.users.CreateUserParams
import bitframe.authentication.users.UsersDao
import bitframe.events.EventBus
import bitframe.events.InMemoryEventBus
import bitframe.server.modules.Module
import bitframe.server.modules.authentication.controllers.AuthenticationController
import bitframe.server.modules.authentication.modules.AuthenticationModuleConfig
import cache.Cache

interface BitframeDaoProvider : AuthenticationDaoProvider {
    
    companion object {
        operator fun invoke(
            users: UsersDao,
            spaces: SpacesDao
        ) = object : BitframeDaoProvider {
            override val users: UsersDao = users
            override val spaces: SpacesDao = spaces
        }

        operator fun invoke(provider: AuthenticationDaoProvider) = invoke(provider.users, provider.spaces)
    }
}