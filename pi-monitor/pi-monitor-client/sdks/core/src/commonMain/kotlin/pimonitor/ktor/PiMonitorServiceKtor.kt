package pimonitor.ktor

import bitframe.BitframeKtorClient
import bitframe.BitframeService
import bitframe.service.config.KtorClientConfiguration
import pimonitor.PiMonitorService
import pimonitor.authentication.signup.SignUpService
import pimonitor.evaulation.business.BusinessService

class PiMonitorServiceKtor(
    configuration: KtorClientConfiguration
) : PiMonitorService(), BitframeService by BitframeKtorClient(configuration) {
    override val signUp: SignUpService = SignUpServiceKtorImpl(configuration)
    override val businesses: BusinessService get() = TODO("Not yet implemented")
}