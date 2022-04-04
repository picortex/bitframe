@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor.client.business.info.forms

import pimonitor.core.businesses.MonitoredBusinessBasicInfo
import presenters.forms.Form
import presenters.forms.FormActionsBuildingBlock
import kotlin.js.JsExport
import pimonitor.client.business.info.fields.BusinessInfoFields as Fields
import pimonitor.core.business.info.params.BusinessInfoParams as Params

class BusinessInfoEditForm(
    business: MonitoredBusinessBasicInfo,
    block: FormActionsBuildingBlock<Params>
) : Form<Fields, Params> by Form(
    heading = "Edit Business information",
    details = "Add or Updated existing information about ${business.name}",
    fields = Fields(business),
    block
)