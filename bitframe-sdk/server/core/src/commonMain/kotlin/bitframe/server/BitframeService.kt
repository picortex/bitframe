package bitframe.server

import bitframe.server.profile.ProfileService
import bitframe.server.signin.SignInService
import bitframe.server.spaces.SpacesService
import kotlin.js.JsExport

open class BitframeService(open val config: ServiceConfig) {
    val signin: SignInService by lazy { SignInService(config) }
    val profile: ProfileService by lazy { ProfileService(config) }
}