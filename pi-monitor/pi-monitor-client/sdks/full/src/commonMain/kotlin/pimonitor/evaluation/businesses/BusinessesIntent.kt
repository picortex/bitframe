@file:JsExport

package pimonitor.evaluation.businesses

import kotlin.js.JsExport

sealed class BusinessesIntent {
    object LoadBusinesses : BusinessesIntent()
    object ShowBusinessForm : BusinessesIntent()
}