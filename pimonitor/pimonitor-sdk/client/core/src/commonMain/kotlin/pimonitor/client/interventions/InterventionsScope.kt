package pimonitor.client.interventions

import bitframe.client.MicroScope
import bitframe.client.UIScopeConfig
import pimonitor.client.PiMonitorApi
import pimonitor.client.utils.disbursables.DisbursablesIntents

internal fun InterventionsScope(config: UIScopeConfig<PiMonitorApi>) = MicroScope {
    viewModel(InterventionsViewModel(config))
    intents(DisbursablesIntents(viewModel))
}