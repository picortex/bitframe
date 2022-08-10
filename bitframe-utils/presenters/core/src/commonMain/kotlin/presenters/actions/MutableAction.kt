@file:Suppress("NON_EXPORTABLE_TYPE")

package presenters.actions

import kotlin.js.JsExport

@JsExport
interface MutableAction<H> : Action<H> {
    override var handler: H
    fun setHandler(h: H)
}