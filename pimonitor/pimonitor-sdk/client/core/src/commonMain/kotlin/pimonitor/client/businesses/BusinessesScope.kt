@file:Suppress("FunctionName")

package pimonitor.client.businesses

import bitframe.client.MiniScope
import bitframe.client.UIScopeConfig
import pimonitor.client.PiMonitorApi

internal fun BusinessesScope(config: UIScopeConfig<PiMonitorApi>) = MiniScope {
    viewModel(BusinessesViewModel(config))
    intents(BusinessesIntents(viewModel))
    constants(BusinessesConstants)
}