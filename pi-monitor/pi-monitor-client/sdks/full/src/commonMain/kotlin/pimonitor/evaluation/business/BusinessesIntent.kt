@file:JsExport

package pimonitor.evaluation.business

import kotlin.js.JsExport

sealed class BusinessesIntent {
    object LoadBusinesses : BusinessesIntent()
}