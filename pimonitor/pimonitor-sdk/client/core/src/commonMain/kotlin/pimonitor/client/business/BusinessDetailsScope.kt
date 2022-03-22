@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor.client.business

import bitframe.client.UIScope
import bitframe.client.UIScopeConfig
import pimonitor.client.businesses.BusinessesService
import pimonitor.core.businesses.MonitoredBusinessBasicInfo
import presenters.cases.State
import kotlin.js.JsExport
import pimonitor.client.business.BusinessDetailsIntent as Intent

open class BusinessDetailsScope(
    override val config: UIScopeConfig<BusinessesService>
) : UIScope<State<MonitoredBusinessBasicInfo>> {
    override val viewModel by lazy { BusinessDetailsViewModel(config) }

    val loadBusiness = { businessId: String ->
        viewModel.post(Intent.LoadBusiness(businessId))
    }
}