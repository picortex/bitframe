package bitframe

import bitframe.authentication.ClientConfiguration
import bitframe.authentication.SignInService
import bitframe.authentication.TestClientConfiguration
import bitframe.authentication.TestSignInService
import kotlin.js.JsExport
import kotlin.jvm.JvmField
import kotlin.jvm.JvmStatic
import kotlin.jvm.JvmSynthetic

interface BitframeTestClient : BitframeService {
    companion object {
        private val cachedClients = mutableMapOf<String, BitframeTestClient>()

        internal val CONFIGURATION = TestClientConfiguration("<test-client>")

        @JvmSynthetic
        operator fun invoke(
            configuration: TestClientConfiguration = CONFIGURATION
        ): BitframeTestClient = cachedClients.getOrPut(configuration.appId) {
            BitframeTestClientImpl(configuration)
        }

        @JvmStatic
        fun with(configuration: TestClientConfiguration) = invoke(configuration)

        @JvmStatic
        fun getDefault() = invoke(CONFIGURATION)
    }

    val configuration: TestClientConfiguration
    override val config: ClientConfiguration
    override val signIn: SignInService
}