package bitframe

import bitframe.authentication.ClientConfiguration
import bitframe.authentication.SignInService
import bitframe.authentication.TestClientConfiguration
import bitframe.authentication.TestSignInService
import kotlin.js.JsExport
import kotlin.jvm.JvmField
import kotlin.jvm.JvmStatic
import kotlin.jvm.JvmSynthetic

@JsExport
open class BitframeTestClient private constructor(configuration: TestClientConfiguration) : BitframeService() {
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
        fun with(configuration: TestClientConfiguration) = invoke(configuration)

        @JvmStatic
        fun getDefault() = invoke(CONFIGURATION)
    }

    override val config: ClientConfiguration = configuration
    override val authentication: SignInService = TestSignInService(configuration)
}