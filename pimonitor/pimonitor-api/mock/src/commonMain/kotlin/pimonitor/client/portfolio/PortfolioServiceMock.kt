package pimonitor.client.portfolio

import bitframe.client.ServiceConfigMock
import pimonitor.core.portfolio.PortfolioDaodService
import pimonitor.core.portfolio.PortfolioServiceCore

class PortfolioServiceMock(
    override val config: ServiceConfigMock
) : PortfolioService, PortfolioServiceCore by PortfolioDaodService(config)