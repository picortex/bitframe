@file:Suppress("FunctionName")

package bitframe.client.events

import bitframe.core.Session
import bitframe.core.events.AuthEventTopics
import events.Event

fun SignedInEvent(session: Session.SignedIn) = Event(session, AuthEventTopics.SIGNED_IN)

fun SignedOutEvent(session: Session.SignedIn) = Event(session, AuthEventTopics.SIGNED_OUT)

fun SwitchedSpaceEvent(session: Session.SignedIn) = Event(session, AuthEventTopics.SPACE_SWITCHED)