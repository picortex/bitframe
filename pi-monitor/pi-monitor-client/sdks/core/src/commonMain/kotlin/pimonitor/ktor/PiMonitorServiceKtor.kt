package pimonitor.ktor

import bitframe.BitframeKtorClient
import bitframe.BitframeService
import bitframe.authentication.KtorClientConfiguration
import pimonitor.PiMonitorService
import pimonitor.authentication.SignUpService
import pimonitor.evaulation.business.BusinessService

class PiMonitorServiceKtor(
    configuration: KtorClientConfiguration
) : PiMonitorService(), BitframeService by BitframeKtorClient(configuration) {
    override val signUp: SignUpService = SignUpServiceKtorImpl(configuration)
    override val business: BusinessService
        get() = TODO("Not yet implemented")
}