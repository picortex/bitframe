package pimonitor.evaluation.contacts

sealed class ContactsIntent {
    object LoadContacts : ContactsIntent()
}