package pimonitor.evaluation.contacts

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import later.await
import live.value
import pimonitor.PiMonitorViewModelConfig
import pimonitor.api.PiMonitorService
import presenters.collections.tableOf
import viewmodel.ViewModel
import pimonitor.evaluation.contacts.ContactsIntent as Intent
import pimonitor.evaluation.contacts.ContactsState as State

class ContactsViewModel(
    config: PiMonitorViewModelConfig
) : ViewModel<Intent, State>(State.Loading("Loading contacts, please wait"), config) {
    private val service: PiMonitorService = config.service
    override fun CoroutineScope.execute(i: pimonitor.evaluation.contacts.ContactsIntent) = when (i) {
        Intent.LoadContacts -> loadContacts()
    }

    private fun CoroutineScope.loadContacts() = launch {
        flow {
            emit(State.Loading("Fetching contacts, please wait"))
            val model = service.businesses.all().await().flatMap { it.contacts.map { contact -> ContactModel(it, contact) } }
            emit(State.Contacts(createContactsTable(model)))
        }.catch {
            emit(State.Failure(it))
        }.collect {
            ui.value = it
        }
    }

    private fun createContactsTable(contacts: List<ContactModel>) = tableOf(contacts) {
        selectable()
        column("Name") { it.data.contact.name }
        column("Position") { it.data.contact.position }
        column("Business") { it.data.business.name }
        column("Email") { it.data.contact.email.value }
    }
}