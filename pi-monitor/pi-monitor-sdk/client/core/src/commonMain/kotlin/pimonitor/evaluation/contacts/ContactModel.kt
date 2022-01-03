@file:JsExport

package pimonitor.evaluation.contacts

import pimonitor.monitored.MonitoredBusiness
import kotlin.js.JsExport

data class ContactModel(
    val business: MonitoredBusiness,
    val contact: MonitoredBusiness.ContactPerson
)