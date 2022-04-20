package pimonitor.client.business.index

import bitframe.client.IndexMicroScope
import bitframe.client.UIScopeConfig
import pimonitor.client.businesses.BusinessesService

internal fun BusinessIndexScope(config: UIScopeConfig<BusinessesService>) = IndexMicroScope {
    viewModel(BusinessIndexViewModel(config))
}