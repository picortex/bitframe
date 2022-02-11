@file:JsExport

package pimonitor.evaluation.contacts

import bitframe.client.UIScope
import pimonitor.PiMonitorScopeConfig
import pimonitor.api.PiMonitorService
import pimonitor.evaluation.contacts.ContactsViewModel
import viewmodel.ViewModel
import kotlin.js.JsExport
import pimonitor.evaluation.contacts.ContactsIntent as Intent
import pimonitor.evaluation.contacts.ContactsState as State

open class ContactsScope(config: PiMonitorScopeConfig) : UIScope<Intent, State> {

    override val service: PiMonitorService = config.service

    override val viewModel: ViewModel<Intent, State> by lazy { ContactsViewModel(config) }

    val loadContacts = {
        viewModel.post(Intent.LoadContacts)
    }
}