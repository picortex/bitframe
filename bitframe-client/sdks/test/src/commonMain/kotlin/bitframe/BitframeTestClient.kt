package bitframe

import bitframe.authentication.TestClientConfiguration
import bitframe.authentication.signin.SignInService
import bitframe.authentication.users.UsersService
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

    override val config: TestClientConfiguration
    override val signIn: SignInService
    override val users: UsersService
}