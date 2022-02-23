package pimonitor.client.portfolio

import bitframe.client.MockServiceConfig
import pimonitor.core.portfolio.PortfolioDaodService
import pimonitor.core.portfolio.PortfolioServiceCore

class PortfolioServiceMock(
    override val config: MockServiceConfig
) : PortfolioService, PortfolioServiceCore by PortfolioDaodService(config)