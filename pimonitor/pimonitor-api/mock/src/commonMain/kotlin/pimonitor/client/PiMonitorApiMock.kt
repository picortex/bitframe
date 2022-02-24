package pimonitor.client

import bitframe.client.BitframeApiMock
import bitframe.client.BitframeApiMockConfig
import pimonitor.client.businesses.BusinessesServiceMock
import pimonitor.client.contacts.ContactsServiceMock
import pimonitor.client.portfolio.PortfolioServiceMock
import pimonitor.client.search.SearchService
import pimonitor.client.search.SearchServiceMock
import pimonitor.client.signup.SignUpServiceMock
import kotlin.jvm.JvmOverloads

class PiMonitorApiMock @JvmOverloads constructor(
    override val config: BitframeApiMockConfig = BitframeApiMockConfig()
) : PiMonitorApi, BitframeApiMock by BitframeApiMock(config) {
    override val signUp by lazy { SignUpServiceMock(config) }
    override val businesses by lazy { BusinessesServiceMock(config) }
    override val contacts by lazy { ContactsServiceMock(config) }
    override val portfolio by lazy { PortfolioServiceMock(config) }
    override val search by lazy { SearchServiceMock(config) }

//    override val portfolio by lazy { PortfolioService(businesses) }
}