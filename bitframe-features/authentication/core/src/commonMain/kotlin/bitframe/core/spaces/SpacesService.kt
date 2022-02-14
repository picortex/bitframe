@file:JsExport

package bitframe.core.spaces

import bitframe.core.Space
import later.Later
import kotlin.js.JsExport

abstract class SpacesService {
    abstract fun register(params: RegisterSpaceParams): Later<Space>
}