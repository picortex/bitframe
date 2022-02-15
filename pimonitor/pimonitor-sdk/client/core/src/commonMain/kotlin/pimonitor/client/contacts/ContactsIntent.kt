package pimonitor.client.contacts

sealed class ContactsIntent {
    object LoadContacts : ContactsIntent()
}