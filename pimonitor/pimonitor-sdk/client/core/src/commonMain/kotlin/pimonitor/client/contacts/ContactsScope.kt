@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor.client.contacts

import bitframe.client.UIScope
import bitframe.client.UIScopeConfig
import pimonitor.client.PiMonitorApi
import viewmodel.ViewModel
import kotlin.js.JsExport
import pimonitor.client.contacts.ContactsIntent as Intent
import pimonitor.client.contacts.ContactsState as State

open class ContactsScope(
    override val config: UIScopeConfig<ContactsService>
) : UIScope<State> {

    override val viewModel by lazy { ContactsViewModel(config) }

    val loadContacts = {
        viewModel.post(Intent.LoadContacts)
    }
}