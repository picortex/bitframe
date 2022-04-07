package pimonitor.client.business.overview

import bitframe.client.MicroScope
import bitframe.client.UIScopeConfig
import pimonitor.client.PiMonitorApi

internal fun BusinessOverviewScope(config: UIScopeConfig<PiMonitorApi>) = MicroScope {
    viewModel(BusinessOverviewViewModel(config))
    intents(BusinessOverviewIntents(viewModel))
}