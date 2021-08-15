package users.server

import users.account.Account
import users.account.CreateAccountParams

fun CreateAccountParams.toAccount(uid: String) = Account(
    uid = uid,
    name = name,
    scope = "",
    type = ""
)