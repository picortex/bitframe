package pimonitor.client.contacts

import bitframe.client.UIScopeConfig
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import pimonitor.client.PiMonitorApi
import viewmodel.ViewModel
import pimonitor.client.contacts.ContactsIntent as Intent
import pimonitor.client.contacts.ContactsState as State

class ContactsViewModel(
    private val config: UIScopeConfig<PiMonitorApi>
) : ViewModel<Intent, State>(State.Loading("Loading contacts, please wait"), config.viewModel) {
    private val service: PiMonitorApi = config.service
    override fun CoroutineScope.execute(i: Intent) = when (i) {
        Intent.LoadContacts -> loadContacts()
    }

    private fun CoroutineScope.loadContacts() = launch {
        flow<State> {
            emit(State.Loading("Fetching contacts, please wait"))
//            val model = service.businesses.all().await().flatMap { it.contacts.map { contact -> ContactModel(it, contact) } }
//            emit(State.Contacts(createContactsTable(model)))
        }.catch {
            emit(State.Failure(it))
        }.collect {
            ui.value = it
        }
    }

//    private fun createContactsTable(contacts: List<ContactModel>) = tableOf(contacts) {
//        selectable()
//        column("Name") { it.data.contact.name }
//        column("Position") { it.data.contact.position }
//        column("Business") { it.data.business.name }
//        column("Email") { it.data.contact.email.value }
//    }
}