package presenters.charts

import kotlin.js.JsExport

@JsExport
interface Chart {
    val title: String
    val description: String
}