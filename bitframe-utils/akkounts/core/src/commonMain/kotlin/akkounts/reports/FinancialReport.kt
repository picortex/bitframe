package akkounts.reports

import kotlin.js.JsExport

@JsExport
interface FinancialReport {
    val uid: String
    val header: FinancialReportHeader
    val body: Any
}