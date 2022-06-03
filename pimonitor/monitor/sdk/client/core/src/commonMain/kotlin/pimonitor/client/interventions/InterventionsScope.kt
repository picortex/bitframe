package pimonitor.client.interventions

import bitframe.client.MicroScope
import bitframe.client.UIScopeConfig
import pimonitor.client.MonitorApi
import pimonitor.client.utils.disbursables.DisbursablesIntents

internal fun InterventionsScope(config: UIScopeConfig<MonitorApi>) = MicroScope {
    viewModel(InterventionsViewModel(config))
    intents(DisbursablesIntents(viewModel))
}