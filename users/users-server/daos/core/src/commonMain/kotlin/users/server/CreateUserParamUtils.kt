package users.server

import users.user.*

fun CreateUserParams.toUser(uid: String) = User(
    uid = uid,
    name = name,
    tag = name,
    contacts = when (credentials) {
        is EmailPassword -> contacts + Contacts.Email((credentials as EmailPassword).email)
        is PhonePassword -> contacts + Contacts.Phone((credentials as PhonePassword).phone)
        is UsernamePassword -> contacts
    },
    photoUrl = null,
    accounts = listOf()
)