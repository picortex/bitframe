@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package bitframe

import kotlin.js.JsExport

interface AppScope<out A> {
    val api: A
}