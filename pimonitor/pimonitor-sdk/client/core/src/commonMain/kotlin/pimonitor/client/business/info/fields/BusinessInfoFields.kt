@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor.client.business.info.fields

import pimonitor.core.businesses.MonitoredBusinessBasicInfo
import presenters.fields.EmailInputField
import presenters.fields.PhoneInputField
import presenters.fields.TextAreaField
import presenters.fields.TextInputField
import kotlin.js.JsExport
import pimonitor.core.business.info.params.BusinessInfoRawFormParams as Params

data class BusinessInfoFields(
    val business: MonitoredBusinessBasicInfo
) {
    val name = TextInputField(
        name = Params::name,
        label = "Business Name",
        hint = "PiCortex LLC",
        value = business.name
    )
    val industry = TextInputField(
        name = Params::industry,
        label = "Industry",
        hint = "Food & Beverages",
        value = business.industry
    )
    val address = TextAreaField(
        name = Params::address,
        label = "Address",
        hint = "Dar es Salaam, Tanzania",
        value = business.address
    )
    val email = EmailInputField(
        name = Params::email,
        label = "Business Email",
        hint = "support@picortex.com",
        value = business.email
    )
    val phone = PhoneInputField(
        name = Params::phone,
        label = "Business Phone",
        value = business.phone
    )
    val website = TextInputField(
        name = Params::website,
        label = "Business Website",
        hint = "https://picortex.com",
        value = business.website
    )
    val about = TextAreaField(
        name = Params::about,
        label = "About",
        hint = "Enter a comprehensive description of this business",
        value = business.about
    )
}