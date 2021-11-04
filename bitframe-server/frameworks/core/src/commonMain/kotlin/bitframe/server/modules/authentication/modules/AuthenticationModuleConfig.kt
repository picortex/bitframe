package bitframe.server.modules.authentication.modules

import bitframe.authentication.signin.Basic
import bitframe.authentication.users.Contacts
import bitframe.authentication.users.CreateUserParams
import bitframe.events.EventBus
import bitframe.server.BitframeApplicationConfig
import bitframe.server.modules.authentication.controllers.AuthenticationController
import bitframe.server.modules.authentication.controllers.AuthenticationControllerImpl
import bitframe.server.modules.authentication.services.AuthenticationService
import bitframe.server.modules.authentication.services.AuthenticationServiceConfig
import bitframe.server.modules.authentication.services.AuthenticationServiceImpl
import cache.Cache
import kotlin.jvm.JvmField

interface AuthenticationModuleConfig {
    val bus: EventBus
    val cache: Cache
    val controller: AuthenticationController
    val defaultUserParams: CreateUserParams

    companion object {
        @JvmField
        val GENESIS = CreateUserParams(
            name = "Genesis",
            contacts = Contacts.None,
            credentials = Basic("ghost@genesis.com", "genesis")
        )

        operator fun invoke(config: BitframeApplicationConfig) = object : AuthenticationModuleConfig {
            val serviceConfig = AuthenticationServiceConfig(config)
            val service = AuthenticationServiceImpl(serviceConfig)
            override val bus: EventBus = config.bus
            override val cache: Cache = config.cache
            override val controller: AuthenticationController = AuthenticationControllerImpl(service)
            override val defaultUserParams: CreateUserParams = GENESIS
        }

        operator fun invoke(config: AuthenticationServiceConfig) = object : AuthenticationModuleConfig {
            val service = AuthenticationServiceImpl(config)
            override val bus: EventBus = config.bus
            override val cache: Cache = config.cache
            override val controller: AuthenticationController = AuthenticationControllerImpl(service)
            override val defaultUserParams: CreateUserParams = GENESIS
        }
    }
}