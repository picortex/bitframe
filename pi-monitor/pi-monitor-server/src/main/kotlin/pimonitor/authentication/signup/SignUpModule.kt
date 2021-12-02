package pimonitor.authentication.signup

import bitframe.server.actions.Action
import bitframe.server.modules.Module

class SignUpModule(
    val controller: SignUpController
) : Module {
    override val name: String = "sign-up"
    override val actions: List<Action> = listOf(
        DefaultSignUpAction(controller)
    )
}