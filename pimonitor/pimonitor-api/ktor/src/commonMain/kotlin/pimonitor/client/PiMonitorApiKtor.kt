package pimonitor.client

import bitframe.client.*
import pimonitor.client.businesses.BusinessesServiceKtor
import pimonitor.client.contacts.ContactsServiceKtor
import pimonitor.client.portfolio.PortfolioServiceKtor
import pimonitor.client.signup.SignUpServiceKtor
import pimonitor.core.portfolio.PortfolioServiceCore

class PiMonitorApiKtor(
    override val config: BitframeApiKtorConfig,
) : PiMonitorApi, BitframeApi by BitframeApiKtor(config) {
    override val signUp by lazy { SignUpServiceKtor(config) }
    override val businesses by lazy { BusinessesServiceKtor(config) }
    override val contacts by lazy { ContactsServiceKtor(config) }
    override val portfolio by lazy { PortfolioServiceKtor(config) }
}