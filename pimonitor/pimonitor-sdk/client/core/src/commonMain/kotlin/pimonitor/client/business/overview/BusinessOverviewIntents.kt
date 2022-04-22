@file:JsExport

package pimonitor.client.business.overview

import pimonitor.core.business.utils.info.LoadInfoRawParams
import viewmodel.ViewModel
import kotlin.js.JsExport

class BusinessOverviewIntents(private val viewModel: ViewModel<BusinessOverviewIntent, *>) {
    val loadOverview = { params: LoadInfoRawParams ->
        viewModel.post(BusinessOverviewIntent.LoadOverview(params))
    }
}