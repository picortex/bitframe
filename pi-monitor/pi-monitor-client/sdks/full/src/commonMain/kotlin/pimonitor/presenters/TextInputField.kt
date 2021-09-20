@file:JsExport

package pimonitor.presenters

import kotlin.js.JsExport

data class TextInputField(
    val label: String,
    val hint: String = label,
    val value: String? = null
)