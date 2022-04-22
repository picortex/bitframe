package pimonitor.client.contacts

import bitframe.client.UIScopeConfig
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import later.await
import pimonitor.core.search.SearchResult
import presenters.cases.Feedback
import presenters.table.builders.tableOf
import viewmodel.ViewModel
import pimonitor.client.contacts.ContactsIntent as Intent
import pimonitor.client.contacts.ContactsState as State

class ContactsViewModel(
    private val config: UIScopeConfig<ContactsService>
) : ViewModel<Intent, State>(State(), config.viewModel) {
    private val service get() = config.service
    override fun CoroutineScope.execute(i: Intent) = when (i) {
        Intent.LoadContacts -> loadContacts()
    }

    private fun CoroutineScope.loadContacts() = launch {
        val state = ui.value
        flow {
            emit(state.copy(status = Feedback.Loading("Fetching contacts, please wait . . .")))
            emit(
                state.copy(
                    status = Feedback.None,
                    table = contactsTableOf(service.all().await())
                )
            )
        }.catch {
            emit(state.copy(status = Feedback.Failure(it, "Failed to fetch contacts")))
            delay(config.viewModel.recoveryTime)
            emit(state)
        }.collect {
            ui.value = it
        }
    }

    internal fun contactsTableOf(contacts: List<SearchResult.ContactPersonSummary>) = tableOf(contacts) {
//        selectable()
        column("Name") { it.data.name }
//        column("Position") { it.data.position }
        column("Business") { it.data.business.name }
        column("Email") { it.data.email }
    }
}