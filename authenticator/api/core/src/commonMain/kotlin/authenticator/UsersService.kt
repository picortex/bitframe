@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package authenticator

import bitframe.client.ServiceConfig
import bitframe.client.logger
import bitframe.core.RequestBody
import bitframe.core.signin.SignInResult
import bitframe.core.users.RegisterUserParams
import later.Later
import later.await
import later.later
import kotlin.js.JsExport

abstract class UsersService(
    private val config: ServiceConfig
) : UsersServiceCore {
    val logger by config.logger()
    fun register(params: RegisterUserParams): Later<SignInResult> = config.scope.later {
        logger.info("Registering user ${params.userName}")
        val rb = RequestBody.UnAuthorized(
            appId = config.appId,
            data = params
        )
        register(rb).await().also { logger.info("Registration completed successfully") }
    }
}