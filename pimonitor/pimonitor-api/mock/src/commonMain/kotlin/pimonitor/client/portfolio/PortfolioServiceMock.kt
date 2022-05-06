package pimonitor.client.portfolio

import pimonitor.client.interventions.InterventionsServiceMock
import pimonitor.client.investments.InvestmentsServiceMock
import pimonitor.core.portfolio.PortfolioServiceCore
import pimonitor.core.portfolio.PortfolioServiceDaod

class PortfolioServiceMock(
    investments: InvestmentsServiceMock,
    interventions: InterventionsServiceMock
) : PortfolioService(investments.config), PortfolioServiceCore by PortfolioServiceDaod(investments.config, investments, interventions)