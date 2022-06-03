@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE", "FunctionName")

package pimonitor.client.businesses.forms

import presenters.forms.Form
import presenters.forms.FormActionsBuildingBlock
import kotlin.js.JsExport
import pimonitor.client.businesses.fields.CreateBusinessFields as Fields
import pimonitor.core.businesses.params.CreateMonitoredBusinessRawParams as Params

internal fun CreateBusinessForm(
    params: Params?,
    block: FormActionsBuildingBlock<Params>
) = Form(
    heading = "Add Business",
    details = "Adding a new business to PiMonitor",
    fields = Fields(params),
    block
)