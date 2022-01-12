@file:JsExport

package pimonitor.evaluation.contacts.exports

import bitframe.client.ReactUIScope
import pimonitor.PiMonitorViewModelConfig
import pimonitor.evaluation.contacts.ContactsIntent as Intent
import pimonitor.evaluation.contacts.ContactsState as State

class ContactsReactScope(config: PiMonitorViewModelConfig) : ContactsScope(config), ReactUIScope<Intent, State>