package bitframe

import bitframe.authentication.ClientConfiguration
import bitframe.authentication.LoginService
import bitframe.authentication.KtorClientConfiguration
import bitframe.authentication.KtorLoginService
import kotlin.js.JsExport
import kotlin.jvm.JvmField

@JsExport
open class BitframeKtorClient(
    val configuration: KtorClientConfiguration
) : BitframeService() {
    override val config: ClientConfiguration = configuration
    override val authentication: LoginService = KtorLoginService(configuration)
}