package pimonitor.monitored

import identifier.Email
import pimonitor.monitored.MonitoredBusiness.ContactPerson
import pimonitor.monitors.MonitorRef

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