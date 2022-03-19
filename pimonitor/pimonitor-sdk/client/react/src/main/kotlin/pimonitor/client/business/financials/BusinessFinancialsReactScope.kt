@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor.client.business.financials

import bitframe.client.ReactUIScope
import bitframe.client.UIScopeConfig
import pimonitor.client.businesses.BusinessesService
import presenters.state.State
import viewmodel.asState

class BusinessFinancialsReactScope(
    override val config: UIScopeConfig<BusinessesService>
) : BusinessFinancialsScope(config), ReactUIScope<State<BusinessFinancialsContent>> {
    override val useScopeState = { viewModel.asState() }
}