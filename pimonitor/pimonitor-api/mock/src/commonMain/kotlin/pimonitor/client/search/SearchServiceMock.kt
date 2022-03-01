package pimonitor.client.search

import bitframe.client.MockServiceConfig
import pimonitor.core.search.SearchDaodService
import pimonitor.core.search.SearchServiceCore

class SearchServiceMock(
    override val config: MockServiceConfig
) : SearchService(config), SearchServiceCore by SearchDaodService(config)