package pimonitor.core.business.overview

operator fun MonitoredBusinessOverview.plus(o: MonitoredBusinessOverview): MonitoredBusinessOverview = when (this) {
    is MonitoredBusinessOverview.FinancialAndOperational -> this
    is MonitoredBusinessOverview.OperationalOnly -> when (o) {
        is MonitoredBusinessOverview.FinancialAndOperational -> o
        is MonitoredBusinessOverview.FinancialOnly -> MonitoredBusinessOverview.FinancialAndOperational(
            business = business,
            cards = o.cards,
            revenueVsExpenses = revenueVsExpenses,
            otherChart = otherChart,
            assets = o.assets,
            balanceSheetChart = o.balanceSheetChart
        )
        is MonitoredBusinessOverview.MissingData -> this
        is MonitoredBusinessOverview.OperationalOnly -> o
    }
    is MonitoredBusinessOverview.FinancialOnly -> when (o) {
        is MonitoredBusinessOverview.FinancialAndOperational -> o
        is MonitoredBusinessOverview.FinancialOnly -> o
        is MonitoredBusinessOverview.MissingData -> this
        is MonitoredBusinessOverview.OperationalOnly -> MonitoredBusinessOverview.FinancialAndOperational(
            business = business,
            cards = cards,
            revenueVsExpenses = o.revenueVsExpenses,
            otherChart = o.otherChart,
            assets = assets,
            balanceSheetChart = balanceSheetChart
        )
    }
    is MonitoredBusinessOverview.MissingData -> o
}

