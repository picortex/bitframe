package bitframe

import bitframe.authentication.ClientConfiguration
import bitframe.authentication.LoginService
import kotlin.js.JsExport

interface BitframeService {
    val config: ClientConfiguration
    val authentication: LoginService
}