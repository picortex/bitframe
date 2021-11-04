package bitframe.server.modules.authentication.modules

import bitframe.server.actions.Action
import bitframe.server.modules.Module

interface AuthenticationModule : Module {
    fun signInAction(): Action
}