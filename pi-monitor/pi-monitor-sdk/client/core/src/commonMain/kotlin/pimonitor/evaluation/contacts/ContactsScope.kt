@file:JsExport

package pimonitor.evaluation.contacts

import pimonitor.PiMonitorScopeConfig
import pimonitor.api.PiMonitorApi
import viewmodel.ViewModel
import kotlin.js.JsExport
import pimonitor.evaluation.contacts.ContactsIntent as Intent
import pimonitor.evaluation.contacts.ContactsState as State

open class ContactsScope(config: PiMonitorScopeConfig) : UIScope<Intent, State> {

    override val api: PiMonitorApi = config.api

    override val viewModel: ViewModel<Intent, State> by lazy { ContactsViewModel(config) }

    val loadContacts = {
        viewModel.post(Intent.LoadContacts)
    }
}