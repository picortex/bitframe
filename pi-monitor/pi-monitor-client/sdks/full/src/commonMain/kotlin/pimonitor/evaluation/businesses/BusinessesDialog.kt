@file:JsExport

package pimonitor.evaluation.businesses

import kotlin.js.JsExport

sealed class BusinessesDialog {
    object None : BusinessesDialog()
    object CreateBusiness : BusinessesDialog()
    object InviteToShareReports : BusinessesDialog()
}