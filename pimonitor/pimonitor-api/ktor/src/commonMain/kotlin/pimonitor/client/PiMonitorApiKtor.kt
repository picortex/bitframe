package pimonitor.client

import bitframe.client.*
import bitframe.client.signin.SignInService
import pimonitor.client.businesses.BusinessesService
import pimonitor.client.businesses.BusinessesServiceKtor
import pimonitor.client.signup.SignUpServiceKtor

class PiMonitorApiKtor(
    override val config: BitframeApiKtorConfig,
) : PiMonitorApi, BitframeApi by BitframeApiKtor(config) {
    override val signUp by lazy { SignUpServiceKtor(config) }
    override val businesses by lazy { BusinessesServiceKtor(config) }
}