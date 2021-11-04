package bitframe.server.modules.authentication.modules

import bitframe.authentication.signin.Basic
import bitframe.authentication.users.Contacts
import bitframe.authentication.users.CreateUserParams
import bitframe.server.actions.Action
import bitframe.server.modules.Module
import kotlin.jvm.JvmField

interface AuthenticationModule : Module {
    fun signInAction(): Action

    companion object {
        @JvmField
        val GENESIS = CreateUserParams(
            name = "Genesis",
            contacts = Contacts.None,
            credentials = Basic("ghost@genesis.com", "genesis")
        )
    }
}