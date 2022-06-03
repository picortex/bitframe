package pimonitor.client.portfolio

sealed class PortfolioIntent {
    object LoadPortfolioData : PortfolioIntent()
}
