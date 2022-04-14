package pimonitor.client.investment.index

import bitframe.client.IndexMicroScope
import bitframe.client.UIScopeConfig
import pimonitor.client.investments.InvestmentsService

internal fun InvestmentIndexScope(config: UIScopeConfig<InvestmentsService>) = IndexMicroScope {
    viewModel(InvestmentIndexViewModel(config))
}