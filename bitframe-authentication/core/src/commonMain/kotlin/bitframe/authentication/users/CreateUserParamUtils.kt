package bitframe.authentication.users

import bitframe.authentication.signin.EmailPassword
import bitframe.authentication.signin.PhonePassword
import bitframe.authentication.signin.UsernamePassword

fun CreateUserParams.toUser(uid: String) = User(
    uid = uid,
    name = name,
    tag = name,
    contacts = when (credentials) {
        is EmailPassword -> contacts + Contacts.Email((credentials).email)
        is PhonePassword -> contacts + Contacts.Phone((credentials).phone)
        is UsernamePassword -> contacts
    },
    photoUrl = null,
    spaces = listOf()
)