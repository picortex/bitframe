package pimonitor.client.portfolio

import bitframe.client.ServiceConfigMock
import pimonitor.core.portfolio.PortfolioServiceDaod
import pimonitor.core.portfolio.PortfolioServiceCore

class PortfolioServiceMock(
    private val config: ServiceConfigMock
) : PortfolioService(config), PortfolioServiceCore by PortfolioServiceDaod(config)