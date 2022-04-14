@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor.client.business.index

import bitframe.client.IndexMicroScope
import bitframe.client.UIScopeConfig
import pimonitor.client.businesses.BusinessesService
import kotlin.js.JsExport

fun BusinessIndexScope(config: UIScopeConfig<BusinessesService>) = IndexMicroScope {
    viewModel(BusinessIndexViewModel(config))
}