@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor.client.business

import bitframe.client.ReactUIScope
import bitframe.client.UIScopeConfig
import pimonitor.client.businesses.BusinessesService
import viewmodel.asState
import pimonitor.client.business.State as State
import pimonitor.client.business.Intent as Intent

class BusinessDetailsReactScope(
    override val config: UIScopeConfig<BusinessesService>
) : BusinessDetailsScope(config), ReactUIScope<Intent, State> {
    override val useScopeState = { viewModel.asState() }
}