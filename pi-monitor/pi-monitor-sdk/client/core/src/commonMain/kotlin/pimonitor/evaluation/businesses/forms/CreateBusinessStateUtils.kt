@file:JsExport

package pimonitor.evaluation.businesses.forms

import kotlin.js.JsExport
import pimonitor.evaluation.businesses.forms.CreateBusinessIntent as Intent

internal fun CreateBusinessState.copy(i: Intent.SubmitForm) = if (this is CreateBusinessState.Form) {
    CreateBusinessState.Form(fields = fields.copy(i), status)
} else {
    this
}