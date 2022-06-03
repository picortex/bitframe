package pimonitor.client.business.info

import bitframe.client.MicroScope
import bitframe.client.UIScopeConfig
import pimonitor.client.MonitorApi

internal fun BusinessInfoScope(config: UIScopeConfig<MonitorApi>) = MicroScope {
    viewModel(BusinessInfoViewModel(config))
    intents(BusinessInfoIntents(viewModel))
}