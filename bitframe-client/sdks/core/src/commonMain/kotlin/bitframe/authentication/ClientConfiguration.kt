package bitframe.authentication

import bitframe.authentication.config.ServiceConfig
import kotlinx.coroutines.CoroutineScope
import kotlin.js.JsExport

open class ClientConfiguration(
    open val appId: String,
    override val scope: CoroutineScope
) : ServiceConfig()
