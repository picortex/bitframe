@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")
package bitframe

import kollections.List
import koncurrent.Later
import kotlin.js.JsExport

interface BulkVoid<out R> {
    fun void(uid: String): Later<R>
    fun voidBulk(ids: List<String>): Later<List<R>>
}