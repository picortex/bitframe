@file:JsExport

package pimonitor.authentication.signup

import pimonitor.presenters.ButtonInputField
import pimonitor.presenters.TextInputField
import kotlin.js.JsExport

data class NameEmailFormParams(
    val title: String,
    val name: TextInputField,
    val email: TextInputField,
    val nextButton: ButtonInputField,
    val prevButton: ButtonInputField
)