@file:JsExport

package pimonitor.evaluation.business.forms

import kotlin.js.JsExport
import pimonitor.evaluation.business.forms.CreateBusinessIntent as Intent

internal fun CreateBusinessState.copy(i: Intent.SubmitForm) = if (this is CreateBusinessState.Form) {
    CreateBusinessState.Form(fields = fields.copy(i), status)
} else {
    this
}