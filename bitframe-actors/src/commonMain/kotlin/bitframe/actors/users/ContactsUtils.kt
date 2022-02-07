package bitframe.actors.users

import kotlinx.collections.interoperable.List
import kotlinx.collections.interoperable.emptyList
import kotlinx.collections.interoperable.listOf
import kotlinx.collections.interoperable.toInteroperableList

fun Contacts.toListOfValues(): List<String> = when (this) {
    Contacts.None -> emptyList()
    is Contacts.Email -> listOf(email.value)
    is Contacts.Phone -> listOf(phone.value)
    is Contacts.Emails -> emails.map { it.value }.toInteroperableList()
    is Contacts.Phones -> phones.map { it.value }.toInteroperableList()
    is Contacts.EmailPhone -> listOf(email.value, phone.value)
    is Contacts.Mixed -> (emails.map { it.value } + phones.map { it.value }).toInteroperableList()
}