package pimonitor.server.signup

import bitframe.server.Action
import bitframe.server.Module

class SignUpModule(
    val controller: SignUpController
) : Module {
    override val name: String = "sign-up"
    override val actions: List<Action> = listOf(
        DefaultSignUpAction(controller)
    )
}