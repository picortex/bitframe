@file:JsExport

package pimonitor.evaluation.contacts.exports

import bitframe.client.ReactUIScope
import pimonitor.api.PiMonitorService
import pimonitor.evaluation.contacts.ContactsIntent as Intent
import pimonitor.evaluation.contacts.ContactsState as State

class ContactsReactScope(service: PiMonitorService) : ContactsScope(service), ReactUIScope<Intent, State>