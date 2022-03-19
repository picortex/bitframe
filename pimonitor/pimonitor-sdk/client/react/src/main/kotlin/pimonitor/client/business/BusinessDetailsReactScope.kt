@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor.client.business

import bitframe.client.ReactUIScope
import bitframe.client.UIScopeConfig
import pimonitor.client.businesses.BusinessesService
import pimonitor.core.businesses.MonitoredBusinessBasicInfo
import presenters.state.State
import viewmodel.asState
import pimonitor.client.business.BusinessDetailsIntent as Intent

class BusinessDetailsReactScope(
    override val config: UIScopeConfig<BusinessesService>
) : BusinessDetailsScope(config), ReactUIScope<State<MonitoredBusinessBasicInfo>> {
    override val useScopeState = { viewModel.asState() }
}