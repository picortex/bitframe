package pimonitor.client.business.overview

import bitframe.client.MicroScope
import bitframe.client.UIScopeConfig
import pimonitor.client.MonitorApi

internal fun BusinessOverviewScope(config: UIScopeConfig<MonitorApi>) = MicroScope {
    viewModel(BusinessOverviewViewModel(config))
    intents(BusinessOverviewIntents(viewModel))
}