package bitframe

import bitframe.authentication.ClientConfiguration
import bitframe.authentication.LoginService
import bitframe.authentication.TestClientConfiguration
import bitframe.authentication.TestLoginService
import kotlin.js.JsExport
import kotlin.jvm.JvmField
import kotlin.jvm.JvmOverloads
import kotlin.jvm.JvmStatic
import kotlin.jvm.JvmSynthetic

@JsExport
open class BitframeTestClient private constructor(configuration: TestClientConfiguration) : BitframeService {
    companion object {
        private val cachedClients = mutableMapOf<String, BitframeTestClient>()

        @JvmField
        val CONFIGURATION = TestClientConfiguration("<test-client>")

        @JvmSynthetic
        operator fun invoke(
            configuration: TestClientConfiguration = CONFIGURATION
        ): BitframeTestClient = cachedClients.getOrPut(configuration.appId) {
            BitframeTestClient(configuration)
        }

        @JvmStatic
        @JvmOverloads
        fun with(configuration: TestClientConfiguration = CONFIGURATION) = invoke(configuration)
    }

    override val config: ClientConfiguration = configuration
    override val authentication: LoginService = TestLoginService(configuration)
}