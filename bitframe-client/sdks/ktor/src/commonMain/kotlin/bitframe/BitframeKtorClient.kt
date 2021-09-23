package bitframe

import bitframe.authentication.ClientConfiguration
import bitframe.authentication.SignInService
import bitframe.authentication.KtorClientConfiguration
import bitframe.authentication.SignInServiceKtor
import kotlin.js.JsExport

@JsExport
open class BitframeKtorClient(
    val configuration: KtorClientConfiguration
) : BitframeService {
    override val config: ClientConfiguration = configuration
    override val signIn: SignInService = SignInServiceKtor(configuration)
}