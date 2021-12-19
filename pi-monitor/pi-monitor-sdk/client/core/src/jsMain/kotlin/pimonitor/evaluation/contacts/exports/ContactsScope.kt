@file:JsExport

package pimonitor.evaluation.contacts.exports

import bitframe.client.UIScope
import pimonitor.api.PiMonitorService
import pimonitor.evaluation.contacts.ContactsViewModel
import viewmodel.ViewModel
import pimonitor.evaluation.contacts.ContactsIntent as Intent
import pimonitor.evaluation.contacts.ContactsState as State

open class ContactsScope(private val service: PiMonitorService) : UIScope<Intent, State> {

    override val viewModel: ViewModel<Intent, State> by lazy { ContactsViewModel(service) }

    val loadContacts = {
        viewModel.post(Intent.LoadContacts)
    }
}