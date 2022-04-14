@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor.client.business

import bitframe.client.ReactUIScope
import bitframe.client.UIScopeConfig
import pimonitor.client.business.index.BusinessIndexScope
import pimonitor.client.businesses.BusinessesService
import pimonitor.core.businesses.MonitoredBusinessBasicInfo
import presenters.cases.State
import viewmodel.asState

class BusinessDetailsReactScope(
    override val config: UIScopeConfig<BusinessesService>
) : BusinessIndexScope(config), ReactUIScope<State<MonitoredBusinessBasicInfo>> {
    override val useScopeState = { viewModel.asState() }
}