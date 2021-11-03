package bitframe

import bitframe.authentication.TestClientConfiguration
import bitframe.authentication.client.signin.SignInService
import bitframe.authentication.users.UsersService
import bitframe.client.BitframeService
import bitframe.events.EventBus
import cache.Cache
import cache.MockCache
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

    override val cache: Cache = MockCache()
    abstract override val signIn: SignInService
    abstract override val users: UsersService
}