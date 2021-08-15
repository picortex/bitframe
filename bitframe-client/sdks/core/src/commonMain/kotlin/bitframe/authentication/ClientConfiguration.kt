package bitframe.authentication

import kotlinx.coroutines.CoroutineScope
import kotlin.js.JsExport

@JsExport
open class ClientConfiguration(
    open val appId: String,
    open val scope: CoroutineScope
)
