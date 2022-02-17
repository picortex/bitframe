package pimonitor.client

import bitframe.client.BitframeApi
import bitframe.client.BitframeApiKtor
import bitframe.client.BitframeApiKtorConfig
import pimonitor.client.signup.SignUpServiceKtor

class PiMonitorApiKtor(
    override val config: BitframeApiKtorConfig,
) : PiMonitorApi, BitframeApi by BitframeApiKtor(config) {
    override val signUp by lazy { SignUpServiceKtor(config) }
}