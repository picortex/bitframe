package pimonitor.client

import bitframe.client.*
import pimonitor.client.businesses.BusinessesServiceKtor
import pimonitor.client.contacts.ContactsServiceKtor
import pimonitor.client.picortex.PiCortexDashboardServiceKtor
import pimonitor.client.portfolio.PortfolioServiceKtor
import pimonitor.client.sage.SageDashboardService
import pimonitor.client.sage.SageDashboardServiceKtor
import pimonitor.client.search.SearchServiceKtor
import pimonitor.client.signup.SignUpServiceKtor

class PiMonitorApiKtor(
    override val config: BitframeApiKtorConfig,
) : PiMonitorApi, BitframeApi by BitframeApiKtor(config) {
    override val signUp by lazy { SignUpServiceKtor(config) }
    override val businesses by lazy { BusinessesServiceKtor(config) }
    override val contacts by lazy { ContactsServiceKtor(config) }
    override val portfolio by lazy { PortfolioServiceKtor(config) }
    override val search by lazy { SearchServiceKtor(config) }
    override val picortex by lazy { PiCortexDashboardServiceKtor(config) }
    override val sage by lazy { SageDashboardServiceKtor(config) }
}