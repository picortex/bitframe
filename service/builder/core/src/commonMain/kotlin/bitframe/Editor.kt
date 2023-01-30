@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package bitframe

import bitframe.actor.Identified
import koncurrent.Later
import kotlin.js.JsExport

interface Editor<in P, out R> {
    fun update(params: Identified<String,P>): Later<R>
}