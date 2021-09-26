package pimonitor.authentication.signup

import bitframe.server.actions.Action
import bitframe.server.modules.Module

class SignUpModule(
    override val name: String = "sign-up",
    val controller: SignUpController
) : Module {
    override val actions: List<Action> = listOf(
        DefaultSignUpAction(controller)
    )
}