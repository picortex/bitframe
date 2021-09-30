package bitframe

import bitframe.authentication.KtorClientConfiguration
import bitframe.authentication.SignInServiceKtor
import bitframe.authentication.signin.SignInService
import bitframe.authentication.users.UsersService
import kotlin.js.JsExport

@JsExport
open class BitframeKtorClient(
    val config: KtorClientConfiguration
) : BitframeService {
    override val users: UsersService get() = TODO("Not yet implemented")
    override val signIn: SignInService = SignInServiceKtor(config)
}