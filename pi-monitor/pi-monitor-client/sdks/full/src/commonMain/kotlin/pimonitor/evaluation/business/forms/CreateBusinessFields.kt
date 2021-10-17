@file:JsExport

package pimonitor.evaluation.business.forms

import bitframe.presenters.fields.ButtonInputField
import bitframe.presenters.fields.TextInputField
import kotlin.js.JsExport

data class CreateBusinessFields internal constructor(
    val inviterIntroduction: String?,
    val title: String,
    val businessName: TextInputField,
    val contactName: TextInputField,
    val contactEmail: TextInputField,
    val submitButton: ButtonInputField
)