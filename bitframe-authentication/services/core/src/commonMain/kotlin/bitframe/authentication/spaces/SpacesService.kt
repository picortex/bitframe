@file:JsExport

package bitframe.authentication.spaces

import later.Later
import kotlin.js.JsExport

abstract class SpacesService {
    abstract fun register(params: RegisterSpaceParams): Later<Space>
}