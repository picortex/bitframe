package bitframe.monitored

import identifier.Email
import bitframe.monitored.MonitoredBusiness.ContactPerson
import bitframe.monitors.MonitorRef

fun CreateMonitoredBusinessParams.toMonitoredBusiness(
    uid: String,
    ref: MonitorRef
) = MonitoredBusiness(
    uid = uid,
    name = businessName,
    contacts = listOf(
        ContactPerson("$uid-1", contactName, "", Email(contactEmail))
    ),
    monitorRef = ref
)