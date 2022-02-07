@file:JsExport

package bitframe.actors.spaces

import later.Later
import kotlin.js.JsExport

abstract class SpacesService {
    abstract fun register(params: RegisterSpaceParams): Later<Space>
}