package pimonitor.core.business.info.params

import validation.requiredNotBlank
import kotlin.js.JsExport

@JsExport
interface BusinessInfoRawFormParams {
    val name: String
    val industry: String
    val address: String
    val phone: String
    val email: String
    val website: String
    val about: String
}

fun BusinessInfoRawFormParams.toValidatedParams(businessId: String) = BusinessInfoParams(
    businessId = businessId.requiredNotBlank("businessId"),
    name = requiredNotBlank(::name),
    industry = industry,
    address = address,
    phone = phone,
    email = email,
    website = website,
    about = about,
)