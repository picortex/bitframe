@file:JsExport

package bitframe.evaluation.businesses

import kotlin.js.JsExport

sealed class BusinessesIntent {
    object LoadBusinesses : BusinessesIntent()
    object ShowBusinessForm : BusinessesIntent()
}