@file:Suppress("NON_EXPORTABLE_TYPE")

package runner

import kotlin.js.JsExport

@JsExport
interface Runner {
    fun start()
    fun run()
}