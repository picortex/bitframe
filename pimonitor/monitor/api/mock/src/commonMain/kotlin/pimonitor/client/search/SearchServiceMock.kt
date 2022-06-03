package pimonitor.client.search

import bitframe.client.ServiceConfigMock
import pimonitor.core.search.SearchServiceDaod
import pimonitor.core.search.SearchServiceCore

class SearchServiceMock(
    override val config: ServiceConfigMock
) : SearchService(config), SearchServiceCore by SearchServiceDaod(config)