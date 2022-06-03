@file:Suppress("FunctionName")

package pimonitor.client.investments

import bitframe.client.MicroScope
import bitframe.client.UIScopeConfig
import pimonitor.client.MonitorApi
import pimonitor.client.utils.disbursables.DisbursablesIntents

internal fun InvestmentScope(config: UIScopeConfig<MonitorApi>) = MicroScope {
    viewModel(InvestmentsViewModel(config))
    intents(DisbursablesIntents(viewModel))
}