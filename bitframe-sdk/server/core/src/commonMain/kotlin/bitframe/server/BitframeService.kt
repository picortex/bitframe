@file:JsExport

package bitframe.server

import bitframe.server.signin.SignInService
import bitframe.server.spaces.SpacesService
import kotlin.js.JsExport

open class BitframeService(open val config: ServiceConfig) {
    val spaces: SpacesService by lazy { SpacesService(config) }
    val signIn: SignInService by lazy { SignInService(config) }
}