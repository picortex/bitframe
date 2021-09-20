@file:JsExport

package pimonitor.evaluation.business

import kotlin.js.JsExport

sealed class BusinessIntent {
    object LoadBusinesses : BusinessIntent()
}