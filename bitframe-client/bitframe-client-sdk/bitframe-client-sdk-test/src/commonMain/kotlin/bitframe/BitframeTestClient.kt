package bitframe

import bitframe.authentication.ClientConfiguration
import bitframe.authentication.LoginService
import bitframe.authentication.TestClientConfiguration
import bitframe.authentication.TestLoginService
import kotlin.js.JsExport
import kotlin.jvm.JvmField

@JsExport
class BitframeTestClient(
    val configuration: TestClientConfiguration
) : BitframeService {
    companion object {
        @JvmField
        val CONFIGURATION = TestClientConfiguration("<test-client>")
    }

    override val config: ClientConfiguration = configuration
    override val authentication: LoginService = TestLoginService(configuration)
}