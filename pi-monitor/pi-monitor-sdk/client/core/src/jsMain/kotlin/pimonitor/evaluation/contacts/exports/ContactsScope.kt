@file:JsExport

package pimonitor.evaluation.contacts.exports

import pimonitor.api.PiMonitorService
import pimonitor.evaluation.contacts.ContactsState
import pimonitor.evaluation.contacts.ContactsViewModel
import useViewModelState
import viewmodel.ViewModel
import pimonitor.evaluation.contacts.ContactsIntent as Intent

class ContactsScope(val service: PiMonitorService) {
    val viewModel: ViewModel<Intent, ContactsState> by lazy {
        ContactsViewModel(service)
    }

    val loadContacts = {
        viewModel.post(Intent.LoadContacts)
    }

    val useStateFromViewModel: () -> ContactsState = {
        useViewModelState(viewModel)
    }
}