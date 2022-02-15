@file:JsExport

package pimonitor.client.businesses.forms

import kotlin.js.JsExport
import pimonitor.client.businesses.forms.CreateBusinessIntent as Intent

internal fun CreateBusinessState.copy(i: Intent.SubmitForm) = if (this is CreateBusinessState.Form) {
    CreateBusinessState.Form(fields = fields.copy(i), status)
} else {
    this
}