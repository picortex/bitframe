@file:JsExport
@file:Suppress("WRONG_EXPORTED_DECLARATION")

package bitframe.api

import bitframe.authentication.client.signin.SignInService
import bitframe.authentication.signin.Session
import bitframe.authentication.spaces.SpacesService
import bitframe.authentication.users.UsersService
import kotlin.js.JsExport

interface BitframeService {
    val config: BitframeServiceConfig
    val spaces: SpacesService
    val users: UsersService
    val signIn: SignInService

    // TODO. These are not being exported to typescript. Why?
    val bus get() = config.bus
    val cache get() = config.cache
    val userLiveSession get() = signIn.session
    val userSession get() = userLiveSession.value as? Session.SignedIn
    val currentUser get() = userSession?.user
    val currentSpace get() = userSession?.space
}