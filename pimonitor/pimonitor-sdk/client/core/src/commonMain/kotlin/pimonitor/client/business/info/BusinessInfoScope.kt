package pimonitor.client.business.info

import bitframe.client.MicroScope
import bitframe.client.UIScopeConfig
import pimonitor.client.PiMonitorApi

fun BusinessInfoScope(config: UIScopeConfig<PiMonitorApi>) = MicroScope {
    viewModel(BusinessInfoViewModel(config))
    intents(BusinessInfoIntents(viewModel))
}