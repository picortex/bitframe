package bitframe.client

import bitframe.core.Session

fun ServiceConfig.getSignedInSessionTo(action: String) = session.value as? Session.SignedIn ?: error("You must be signed in to $action")