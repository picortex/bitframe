@file:JvmName("Credentials")

import akkounts.sage.SageOneZAUserCompany
import kotlin.jvm.JvmField
import kotlin.jvm.JvmName

const val API_KEY = "{C7542EBF-4657-484C-B79E-E3D90DB0F0D1}"

@JvmField
val COMPANY_PICORTEX = SageOneZAUserCompany(
    uid = "<unset>",
    name = "PiCortex",
    username = "support@picortex.com",
    password = "daub4foc!BIRN.teab",
    companyId = "13956",
)

@JvmField
val PICORTEX_DEVELOPERS = SageOneZAUserCompany(
    uid = "<unset>",
    name = "PiCortex Sage Developers Inc",
    username = "developer.picortex.sage@gmail.com",
    password = "Developer.Picortex.Sage@2021",
    companyId = "499076",
)

const val PROD_API_KEY = "{C7542EBF-4657-484C-B79E-E3D90DB0F0D1}"

@JvmField
val PRODUCTION_ACCOUNT = SageOneZAUserCompany(
    uid = "<unset>",
    name = "PiCortex Sage Developers Inc",
    username = "mmajapa@gmail.com",
    password = "Rondebosch2016@",
    companyId = "468271",
)