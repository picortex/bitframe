package pimonitor.client.signup

import events.Event
import pimonitor.core.signup.SignUpResult

class SignUpEvent(data: SignUpResult) : Event<SignUpResult>(data, TOPIC) {
    companion object {
        const val TOPIC = "pimonitor.authentication.signup"
    }
}