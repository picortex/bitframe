package users.user

fun Contacts.toListOfValues(): List<String> = when (this) {
    Contacts.None -> emptyList()
    is Contacts.Email -> listOf(email.value)
    is Contacts.Phone -> listOf(phone.value)
    is Contacts.Emails -> emails.map { it.value }
    is Contacts.Phones -> phones.map { it.value }
    is Contacts.EmailPhone -> listOf(email.value, phone.value)
    is Contacts.Mixed -> emails.map { it.value } + phones.map { it.value }
}