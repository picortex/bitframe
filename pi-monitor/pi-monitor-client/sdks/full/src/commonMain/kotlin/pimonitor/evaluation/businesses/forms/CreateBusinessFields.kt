@file:JsExport

package pimonitor.evaluation.businesses.forms

import presenters.fields.BooleanInputField
import presenters.fields.ButtonInputField
import presenters.fields.TextInputField
import kotlin.js.JsExport

data class CreateBusinessFields internal constructor(
    val inviterIntroduction: String?,
    val title: String,
    val businessName: TextInputField,
    val contactName: TextInputField,
    val contactEmail: TextInputField,
    val sendInvite: BooleanInputField?,
    val submitButton: ButtonInputField
)