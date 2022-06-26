@file:JsExport

package bitframe.core

import kotlinx.serialization.Serializable
import kotlin.js.JsExport

@Deprecated("In favour of bitframe.actor")
@Serializable
data class App(val uid: String)
