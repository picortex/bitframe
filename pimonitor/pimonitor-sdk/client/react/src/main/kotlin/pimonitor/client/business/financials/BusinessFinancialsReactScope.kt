@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor.client.business.financials

import bitframe.client.ReactUIScope
import bitframe.client.UIScopeConfig
import pimonitor.client.businesses.BusinessesService
import viewmodel.asState

class BusinessFinancialsReactScope(
    override val config: UIScopeConfig<BusinessFinancialsService>
) : BusinessFinancialsScope(config), ReactUIScope<BusinessFinancialsState> {
    override val useScopeState = { viewModel.asState() }
}