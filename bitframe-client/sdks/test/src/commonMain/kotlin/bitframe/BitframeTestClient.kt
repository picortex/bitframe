package bitframe

import bitframe.authentication.TestClientConfiguration
import bitframe.authentication.signin.SignInService
import bitframe.authentication.users.UsersService
import bitframe.events.EventBus
import kotlin.jvm.JvmStatic
import kotlin.jvm.JvmSynthetic

abstract class BitframeTestClient : BitframeService() {
    companion object {
        private val cachedClients = mutableMapOf<String, BitframeTestClient>()

        internal val CONFIGURATION = TestClientConfiguration("<test-client>")

        @JvmSynthetic
        operator fun invoke(
            configuration: TestClientConfiguration = CONFIGURATION,
            bus: EventBus
        ): BitframeTestClient = cachedClients.getOrPut(configuration.appId) {
            BitframeTestClientImpl(bus, configuration)
        }

        @JvmStatic
        fun with(configuration: TestClientConfiguration, bus: EventBus) = invoke(configuration, bus)

        @JvmStatic
        fun getDefault(bus: EventBus) = invoke(CONFIGURATION, bus)
    }

    abstract override val signIn: SignInService
    abstract override val users: UsersService
}