package bitframe.authentication.users

import bitframe.actors.users.Contacts
import bitframe.actors.users.User
import bitframe.authentication.signin.EmailPassword
import bitframe.authentication.signin.PhonePassword
import bitframe.authentication.signin.UsernamePassword
import kotlinx.collections.interoperable.listOf

fun CreateUserParams.toUser(uid: String) = User(
    uid = uid,
    name = name,
    tag = name,
    contacts = listOf(),
//    contacts = when (credentials) {
//        is EmailPassword -> contacts + Contacts.Email((credentials).email)
//        is PhonePassword -> contacts + Contacts.Phone((credentials).phone)
//        is UsernamePassword -> contacts
//    },
    photoUrl = null,
    spaces = listOf()
)