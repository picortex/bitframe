package pimonitor.client.business.investments

import bitframe.client.MicroScope
import bitframe.client.UIScopeConfig
import pimonitor.client.PiMonitorApi
import pimonitor.client.investments.InvestmentIntents
import pimonitor.client.investments.InvestmentsViewModel

fun BusinessInvestmentsScope(config: UIScopeConfig<PiMonitorApi>) = MicroScope {
    viewModel(InvestmentsViewModel(config))
    intents(InvestmentIntents(viewModel))
}