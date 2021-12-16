@file:JsExport

package bitframe.client

import bitframe.api.BitframeService
import kotlin.js.JsExport

open class BitframeScope(
    val service: BitframeService
)