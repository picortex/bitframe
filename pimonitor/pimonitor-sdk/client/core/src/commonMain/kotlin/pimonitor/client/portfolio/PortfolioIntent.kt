package pimonitor.client.portfolio

sealed class PortfolioIntent {
    object LoadPortfolio : PortfolioIntent()
}
