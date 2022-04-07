@file:Suppress("FunctionName")

package pimonitor.client.events

import events.Event
import pimonitor.core.events.PimonitorEventTopics
import pimonitor.core.signup.SignUpResult

fun SignedUpEvent(data: SignUpResult) = Event(data, PimonitorEventTopics.SIGNED_UP)