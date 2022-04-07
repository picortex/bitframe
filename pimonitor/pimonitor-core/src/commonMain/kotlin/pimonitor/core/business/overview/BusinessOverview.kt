@file:JsExport
package pimonitor.core.business.overview

import kotlin.js.JsExport

data class BusinessOverview(
    val businessId: String,
    val info: String,
)