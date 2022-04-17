package pimonitor.client.investment.index

import bitframe.client.UIScopeConfig
import pimonitor.client.investments.InvestmentsService
import pimonitor.client.utils.disbursable.index.DisburasableIndexScope

internal fun InvestmentIndexScope(config: UIScopeConfig<InvestmentsService>) = DisburasableIndexScope(config)