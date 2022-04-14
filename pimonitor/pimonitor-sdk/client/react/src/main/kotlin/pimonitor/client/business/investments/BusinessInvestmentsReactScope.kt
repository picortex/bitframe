@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor.client.business.investments

import bitframe.client.ReactUIScope
import bitframe.client.UIScopeConfig
import pimonitor.client.PiMonitorApi
import pimonitor.core.investments.Investment
import presenters.cases.CrowdState
import viewmodel.asState

class BusinessInvestmentsReactScope(
    override val config: UIScopeConfig<PiMonitorApi>
) : BusinessInvestmentsScopeLegacy(config), ReactUIScope<CrowdState<Investment>> {
    override val useScopeState = { viewModel.asState() }
}