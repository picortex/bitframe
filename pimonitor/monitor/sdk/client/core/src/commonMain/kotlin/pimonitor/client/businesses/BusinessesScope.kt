@file:Suppress("FunctionName")

package pimonitor.client.businesses

import bitframe.client.MiniScope
import bitframe.client.UIScopeConfig
import pimonitor.client.MonitorApi

internal fun BusinessesScope(config: UIScopeConfig<MonitorApi>) = MiniScope {
    viewModel(BusinessesViewModel(config))
    intents(BusinessesIntents(viewModel))
    constants(BusinessesConstants)
}