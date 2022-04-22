package pimonitor.core.sage

import akkounts.sage.SageOneZAUserCompany
import pimonitor.core.businesses.MonitoredBusinessBasicInfo

internal fun SageApiCredentials.toCompany(business: MonitoredBusinessBasicInfo) = SageOneZAUserCompany(
    uid = business.uid,
    name = business.name,
    username = username,
    password = password,
    companyId = companyId
)