@file:JsExport

package bitframe

import bitframe.authentication.signin.SignInService
import bitframe.authentication.spaces.SpacesService
import bitframe.authentication.users.UsersService
import cache.Cache
import kotlin.js.JsExport

abstract class BitframeService {
    abstract val cache: Cache
    abstract val spaces: SpacesService
    abstract val users: UsersService
    abstract val signIn: SignInService
}