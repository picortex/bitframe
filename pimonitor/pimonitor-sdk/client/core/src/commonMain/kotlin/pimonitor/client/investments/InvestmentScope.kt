package pimonitor.client.investments

import bitframe.client.MicroScope
import bitframe.client.UIScopeConfig
import pimonitor.client.PiMonitorApi

@Suppress("FunctionName")
internal fun InvestmentScope(config: UIScopeConfig<PiMonitorApi>) = MicroScope {
    viewModel(InvestmentsViewModel(config))
    intents(InvestmentIntents(viewModel))
}